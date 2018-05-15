package com.hub.services;

import java.sql.Blob;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hub.dao.CommunityBulletinDAO;
import com.hub.models.CommunityEvent;

@Service
public class DataTypeConvertService {
	
	@Autowired
	CommunityBulletinDAO cbDAO;
	CommunityEvent ceDAO;
	
	
	public byte[] blobToByteArray(Blob blob) throws SQLException{
		int blobLength = (int) blob.length();  
		byte[] blobAsBytes = blob.getBytes(1, blobLength);
		blob.free();
		return blobAsBytes;
	}
	
	public Blob byteArrayToBlob(byte[] blobAsBytes) throws SerialException, SQLException {
		
		Blob blob = new javax.sql.rowset.serial.SerialBlob(blobAsBytes);
		return blob;
	}
	
	

}
