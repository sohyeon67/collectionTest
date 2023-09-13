package kr.or.ddit.basic.tcp;

import java.net.Socket;

public class TcpClient03 {

	public static void main(String[] args) {
		try {
			//Socket socket = new Socket("192.168.36.116", 7777);	// 이 서버에 접속
			Socket socket = new Socket("localhost", 7777);	// 이 서버에 접속
			System.out.println("서버에 연결되었습니다...");
			System.out.println();
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
