package TCPCM;

import java.io.*;
import java.net.*;

import SETTINGS.TCP; 

public class TCPCM {
	
	private ObjectOutputStream writer = null;
	private ObjectInputStream reader = null;
	
	public TCPCM() {
		
	}
	
	public boolean connectServer(String serverIP, String nickname) {
		try {
			Socket socket = new Socket(serverIP, TCP.PORT);
			writer = new ObjectOutputStream(socket.getOutputStream());
			reader = new ObjectInputStream(socket.getInputStream());
			writer.writeObject(Protocols.Action.CH_NAME);
			writer.writeObject(nickname);
			writer.flush();
			new Handler(socket).start();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/*
	 * move attack
	 */
	public void keyChange(Protocols.Action action) {
		try {
			writer.writeObject(action);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void chooseTeam(Protocols.Team team) {
		try {
			writer.writeObject(Protocols.Action.CH_TEAM);
			writer.writeObject(team);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void chooseRole(Protocols.Role role) {
		try {
			writer.writeObject(Protocols.Action.CH_ROLE);
			writer.writeObject(role);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class Handler extends Thread {
		private Socket socket = null;
		
		Handler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
		}
	}
}
