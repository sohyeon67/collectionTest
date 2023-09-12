package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {

	public static void main(String[] args) {
		try {
			// TCP소켓 통신을 하기 위해 ServerSocket객체를 생성한다.
			ServerSocket server = new ServerSocket(7777);
			
			System.out.println("클라이언트의 접속을 기다립니다...");
			
			// accept()메서드
			//		==> 클라이언트에서 연결 요청이 올 때까지 기다린다.
			//		==> 연결 요청이 오면 새로운 Socket객체를 생성해서
			//			클라이언트의 Socket과 연결한다.
			Socket socket = server.accept();
			
			// 이 부분부터는 클라이언트와 연결이 완료된 이후에 
			// 처리할 내용을 작성한다.
			System.out.println("클라이언트와 연결되었습니다...");
			System.out.println();
			
			// 상대방과 연결된 Socket객체를 이용하여 상대방의 정보를 구할 수 있다.
			System.out.println("접속된 상대방 정보");
			System.out.println("IP주소 : " + 
							socket.getInetAddress().getHostAddress());
			System.out.println("Port번호 : " + socket.getPort());
			System.out.println();
			
			System.out.println("연결된 내 컴의 정보");
			System.out.println("IP주소 : " + socket.getLocalAddress());
			System.out.println("Port번호 : " + socket.getLocalPort());
			System.out.println();
			
			// 클라이언트에게 메시지 보내기
			// Socket의 OutputStream객체를 이용해서 전송한다.
			// ( Socket의 getOutputStream()메서드로 스트림 객체를 구한다.)
			OutputStream out = socket.getOutputStream();
			DataOutputStream dout = new DataOutputStream(out);
			
			// 클라이언트에게 데이터(메시지) 보내기
			// ==> Socket을 이용해서 구한 OutputStream의 
			//		출력 메서드를 사용하면 된다.
			dout.writeUTF("환영합니다... 어서오세요...");
			
			System.out.println("메시지를 보냈습니다...");
			
			// 사용했던 스트림과 소켓 닫기
			dout.close();
			socket.close();
			server.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
