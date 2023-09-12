package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TcpClient01 {

	public static void main(String[] args) {
	/*
		통신에서 현재 자신의 컴퓨터를 나타내는 방법
		1) 원래의 IP주소 : 예) 192.168.36.2
		2) 지정된 IP주소 : 127.0.0.1
		3) 원래의 컴퓨터 이름 : 예) DESKTOP-1SABI0A
		4) 지정된 컴퓨터 이름 : localhost
	*/
		try {
			String serverIp = "localhost";
			
			System.out.println(serverIp + " 서버에 연결 중 입니다...");
			
			// 서버의 IP주소와 Port번호를 지정하여 Socket객체를 생성한다.
			// Socket객체는 생성이 완료되면 자동으로 지정된
			// 서버로 요청 신호를 보낸다.
			Socket socket = new Socket(serverIp, 7777);
			
			// 이 부분부터는 서버와 연결된 이후에 처리할 내용을 작성한다.
			System.out.println("서버에 연결되었습니다...");
			System.out.println();
			
			// 서버가 보내온 메시지를 받아서 화면에 출력하기
			
			// Socket을 이용하여 InputStream객체를 구한다.
			// ==> Socket객체의 getInputStream()메서드 이용한다.
			InputStream in = socket.getInputStream();
			DataInputStream din = new DataInputStream(in);
			
			// 스트림 객체의 읽기 메서드를 사용해서 데이터를 받는다.
			System.out.println("서버에서 보내온 메시지 : " + din.readUTF());
			System.out.println();
			
			System.out.println("연결을 종료합니다....");
			
			// 스트림과 소켓 닫기
			din.close();
			socket.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
