'use strict';

var stompClient = null;
var user = null;
var openChats = [];
var numChatsOpen = 0;
var testCommunity = new Group('Community', 1, true);

function init(userJson, activeUsersJson){
	user = JSON.parse(userJson);
	var activeUsers = JSON.parse(activeUsersJson);

	connect();
	displayActive([testCommunity], activeUsers);
	
	window.setInterval(function(){
		$.ajax({
		    type: 'GET',
		    url: '/DesignHub/getActiveUsers',
		    success: function(result){
		    	displayActive([testCommunity], JSON.parse(result));
		    }
		 });
	}, 5000);
}

function displayActive(activeGroups, activeUsers){
	$('#activeUsersList').empty();
	
	for(var i = 0; i < activeGroups.length; i++){
		var group = activeGroups[i];
		
		var groupElement = document.createElement('div');
		var groupText = document.createTextNode(group.name + " (Group)");
		groupElement.appendChild(groupText);
		
		$(groupElement).click(function(){
			openChat({isGroup: true, recipient: group, lastLoadedDate: null});
		});
		$('#activeUsersList').append(groupElement);
	}
	
	for(var i = 0; i < activeUsers.length; i++){
		var user = activeUsers[i];
		var usernameElement = document.createElement('div');
		var usernameText = document.createTextNode(user.firstName + " " + user.lastName);
		
		usernameElement.appendChild(usernameText);
		//Store these for access in anonymous listener
		usernameElement.userId = user.id;
		usernameElement.userFName = user.firstName;
		usernameElement.userLName = user.lastName;
		usernameElement.userEmail = user.email;
		$(usernameElement).click(function(e){
			openChat({isGroup: false, recipient: {id: e.target.userId,firstName: e.target.userFName, lastName: e.target.userLName, email: e.target.userEmail }, lastLoadedDate: null});
		});
		//usernameElement.classList.add('user');
		
		$('#activeUsersList').append(usernameElement);
	}
}

function openChat(recipientWrapper){
	var recipient = recipientWrapper.recipient;
	var isGroup = recipientWrapper.isGroup;
	
	var alreadyOpen = isRecipientChatOpen(recipient);
	
	if(!alreadyOpen){
		var position = 290 + (numChatsOpen * 290);
		
		var chatElement = document.createElement('div');
		chatElement.classList.add('fixedBox');
		
		var chatHeader = document.createElement('div');
		chatHeader.classList.add('fixedBoxHeader');
		var chatHeaderText = document.createTextNode(isGroup ? recipient.name : (recipient.firstName + " " + recipient.lastName));
		chatHeader.appendChild(chatHeaderText);
		$(chatHeader).click(function (){
			closeChat(recipientWrapper);
		});
		
		var chatBody = document.createElement('div');
		chatBody.classList.add('chatBoxBody');
		
		var chatFooter = document.createElement('div');
		chatFooter.classList.add('chatBoxFooter');
		
		var chatForm = document.createElement('div');
		var chatInput = document.createElement('input');
		var jChatInput = $(chatInput);
		jChatInput.attr("type", "text");
		jChatInput.attr("placeHolder", "Type a message...");
		jChatInput.attr("autocomplete", "off");
		
		chatInput.onkeydown = function(e){
			if(e.keyCode == 13){
				var messageContent = chatInput.value.trim();
			    var date = new Date();
			    if(messageContent && stompClient) {
			        var chatMessage = {
			        	sender: {
			        		id: user.id,
			        		firstName: user.firstName,
			        		lastName:user.lastName,
			        		email: user.email
			        	},
			            senderId: user.id,
			            recipientId: recipient.id,
			            dateSent: date,
			            group: isGroup,
			            content: messageContent
			        };
			        if(isGroup){
			        	stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
			        } else {
			        	messageAppend(chatMessage, true);
			        	stompClient.send("/app/chat.sendMessageToUser", {}, JSON.stringify(chatMessage));
			        }
			       
			        chatInput.value = '';
			    }
			    e.preventDefault();
			}
		};
		
		chatForm.appendChild(chatInput);
		chatFooter.appendChild(chatForm);
		
		chatElement.appendChild(chatHeader);
		chatElement.appendChild(chatBody);
		chatElement.appendChild(chatFooter);
		
		$(chatElement).css("right", position + "px");
		$(chatElement).attr("id", recipient.id);
		
		$('body').append(chatElement);
		
		//By using a string instead of an int, speeds up array.splice by several seconds.
		openChats["i" + recipient.id] = true;
		numChatsOpen += 1;
		
		load10Messages(recipientWrapper);
	}
}

function closeChat(recipientWrapper){
	openChats["i" + recipientWrapper.recipient.id] = false;
	$('#' + recipientWrapper.recipient.id).remove();
	numChatsOpen -= 1;
}

function load10Messages(recipientWrapper){
	
	//TODO Loading wheel while this is loading...
	
	var recipient = recipientWrapper.recipient;
	var isGroup = recipientWrapper.isGroup;
	
	if(recipientWrapper.lastLoadedDate == null){
		recipientWrapper.lastLoadedDate = new Date();
	}
	
	var url = "/DesignHub/load10MessagesConversation/" + user.id + "/" + recipient.id + "/" + recipientWrapper.lastLoadedDate;
	if(isGroup){
		url = "/DesignHub/load10MessagesGroup/" + recipient.id + "/" + recipientWrapper.lastLoadedDate;
	}
	
	 $.ajax({
	    type: 'GET',
	    url: url,
	    success: function(result){
	    	//TODO Get earliest message date, set to lastLoadedDate
	    	//Only needed if we enable loading more messages
	    	prependMessages(result, recipientWrapper);
	    }
	 });
}

function connect() {
    if(user.email) {
        var socket = new SockJS('/DesignHub/ws');
        stompClient = Stomp.over(socket);
        socket.onopen= function() {
            console.log('open');
            socket.send('test');
        };
        stompClient.connect({}, onConnected, onError);
    	
    }
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    messageAppend(message, false);
}

function messageAppend(message, messageFix){
	var messageElement = createMessageElement(message);

    var bodyId = message.senderId;
    if(message.group || messageFix){
    	bodyId = message.recipientId;
    }
    console.log("Appending message: " + bodyId);
    var messageArea = $('#' + bodyId + " > .chatBoxBody");
    messageArea.append(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function onConnected() {
	//Needs changing if there are ever multiple groups
	stompClient.subscribe('/topic/public', onMessageReceived);
    stompClient.subscribe('/user/queue/message', onMessageReceived);
}



function prependMessages(messages, recipientWrapper){
	var messages = JSON.parse(messages);
	for(var i = 0; i < messages.length; i++){
		var messageElement = createMessageElement(messages[i]);
		var messageArea = $('#' + recipientWrapper.recipient.id + " > .chatBoxBody");
		messageArea.prepend(messageElement)
		messageArea.scrollTop = messageArea.scrollHeight;
		if(i == messages.length - 1){
			recipientWrapper.lastLoadedDate = messages[i].dateSent;
		}
	}
}

function createMessageElement(message){
	var messageElement = document.createElement('li');

    var usernameElement = document.createElement('span');
    var usernameText = document.createTextNode(message.sender.firstName + " " + message.sender.lastName);
    
    usernameElement.appendChild(usernameText);
    messageElement.appendChild(usernameElement);

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    if(message.sender.email === user.email){
    	textElement.classList.add("sent");
    }
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);
    
    return messageElement;
}

function isRecipientChatOpen(recipient){
	return (openChats["i" + recipient.id] === true);
}

function onError(error) {
	//TODO make this into an alert/toast
    //connectingElement.textContent = 'Could not connect to chat server. Please refresh this page to try again!';
    //connectingElement.style.color = 'red';
}

function Group(name, id, active){
	this.name = name;
	this.id = id;
	this.active = active;
}