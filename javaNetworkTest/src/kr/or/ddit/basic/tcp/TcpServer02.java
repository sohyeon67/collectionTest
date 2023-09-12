package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {

	public static void main(String[] args) {
		try {
			// TCP소켓 통신을 하기 위해 ServerSocket객체를 생성한다.
			ServerSocket server = new ServerSocket(7777);
			
			System.out.println("클라이언트의 접속을 기다립니다...");
			
			Socket socket = server.accept();
			
			System.out.println("클라이언트와 연결되었습니다...");
			System.out.println();

			// 데이터 송수신을 위한 스트림 객체 생성
			// 수신용
			DataInputStream din = new DataInputStream(socket.getInputStream());
			
			// 송신용
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			while(true) {
				// 클라이언트가 보낸 메시지를 받는다.
				String str = din.readUTF();
				if(str.equals("/end")) {
					break;
				}
				System.out.println("클라이언트가 보낸 메시지 : " + str);
				
				// 클라이언트가 보낸 메시지를 그대로 돌려 준다.
				dout.writeUTF(str);
				
			}
			
			dout.close();
			socket.close();
			server.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
