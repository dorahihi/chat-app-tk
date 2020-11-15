package com.chatApp.sp.utils;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.users.FullAccount;

@Component
public class DropboxUtils {

	private static final String ACCESS_TOKEN = "TW2N1u5W4IAAAAAAAAAAAS3QDBC2l_Ot6vMXAS0BxeGq4uistBg_1q_tgjTsTZEy";
	
	private static DbxClientV2 client;
	
	public DropboxUtils() throws DbxApiException, DbxException, IOException {
		DbxRequestConfig config = DbxRequestConfig.newBuilder("chatapp").build();
        DropboxUtils.client = new DbxClientV2(config, ACCESS_TOKEN);
        FullAccount account = client.users().getCurrentAccount();
        System.out.println("in dropboxUtils: dropbox account information: "+account.getName().getDisplayName());
        
	}
	
	public static String uploadFile(InputStream in, String fileName) throws UploadErrorException, DbxException, IOException {
        FileMetadata metadata = DropboxUtils.client.files().uploadBuilder("/"+fileName).uploadAndFinish(in);
        SharedLinkMetadata sharedLink = client.sharing().createSharedLinkWithSettings("/"+fileName);
        return convertToRawLink(sharedLink.getUrl());
	}
	
	public static void getFiles() throws ListFolderErrorException, DbxException  {
		ListFolderResult result = client.files().listFolder("");
		while (true) {
		    for (Metadata metadata : result.getEntries()) {
		        System.out.println(metadata.getPathLower());
		    }

		    if (!result.getHasMore()) {
		    	System.out.println("There is no more files!!");
		        break;
		    }

		    result = client.files().listFolderContinue(result.getCursor());
		}
	}
		
	private static String convertToRawLink(String sharedLink) {
		String rawLink = sharedLink.substring(0, sharedLink.length()-4)+"raw=1";		
		return rawLink;
	}
	
}
