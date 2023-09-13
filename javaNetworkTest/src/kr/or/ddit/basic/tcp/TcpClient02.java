package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		try {
			String serverIp = "localhost";
			System.out.println(serverIp + " 서버에 연결 중 입니다...");

			Socket socket = new Socket(serverIp, 7777);

			// 이 부분부터는 서버와 연결된 이후에 처리할 내용을 작성한다.
			System.out.println("서버에 연결되었습니다...");
			System.out.println();

			// 데이터 송수신을 위한 스트림 객체 생성
			// 수신용
			DataInputStream din = new DataInputStream(socket.getInputStream());

			// 송신용
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

			while (true) {
				System.out.print("서버로 보낼 메시지 입력 >> ");
				String str = scan.nextLine();
				
				// 입력받은 메시지를 서버로 전송한다.
				dout.writeUTF(str);
				
				if(str.equals("/end")) {
					break;
				}
				
				System.out.println("서버가 보낸 메시지 : " + din.readUTF());
			}

		
			System.out.println("연결을 종료합니다....");

			// 스트림과 소켓 닫기
			din.close();
			socket.close();

		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
