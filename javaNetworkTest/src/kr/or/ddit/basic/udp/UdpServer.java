package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
	UDP방식 : 비연결 지향, 신뢰성이 없다. 데이터가 순서대로 도착한다는 보장이 없다.
			그렇지만 TCP방식보다 속도가 빠르다.
			
	DatagramSocket객체와 DatagramPacket객체를 이용하여 통신한다.
	- DatagramSocket객체 ==> 데이터의 송수신과 관련된 작업을 수행한다.(우체부)
	- DatagramPacket객체 ==> 주고 받는 실제 데이터와 관련된 작업을 수행한다.(소포)
			==> 수신용을 위한 생성자와 송신용을 위한 생성자를 따로 제공한다.
			
	TCP는 스트림을 이용해서 데이터를 송수신 하지만
	UDP는 데이터그램을 이용하여 데이터를 송수신 한다.
	
*/


public class UdpServer {

	public static void main(String[] args) {
		try {
			// 통신할 포트를 지정하여 소켓 객체를 생성한다.
			DatagramSocket socket = new DatagramSocket(8888);
			
			// 수신용 패킷 객체 변수와 송신용 패킷 객체 변수 선언
			DatagramPacket inpacket, outpacket;
			System.out.println("서버 실행 중...");
			
			while(true) {
				// 상대방이 보낸 자료를 받아 오기
				
				// 수신 받은 데이터가 저장될 byte형 배열 선언
				byte[] bMsg = new byte[512];
				
				// 수신용 패킷 객체 생성
				// ==> 데이터가 저장될 byte형 배열과 그 배열의 길이를 생성자의
				// 		인수값으로 지정하여 수신용 패킷 객체를 생성한다.
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 데이터를 수신한다. ==> 소켓의 receive() 메서드를 이용한다.
				// receive()메서드는 데이터가 올 때까지 기다린다.
				// 수신된 데이터의 패킷 정보는 수신용 패킷 객체를 생성할 때
				// 생성자에 지정한 패킷 객체에 저장된다.
				socket.receive(inpacket);
				
				// 수신 받은 패킷에서 상대방의 주소 정보, 포트 번호 등을 알 수 있다.
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				System.out.println("상대방의 IP정보 : " + address);
				System.out.println("상대방의 Port 번호 : " + port);
				System.out.println();
				
				// 상대방이 보낸 메시지 출력하기
				// 수신용 패킷 객체.getData() ==> 실제 수신한 데이터를 byte형 배열로 반환한다.
				//		==> 수신용 패킷 객체를 생성할 때 지정한 byte형 배열에 
				//			수신한 데이터가 저장된다.
				// 수신용 패킷 객체.getLength() ==> 실제 수신한 데이터의 길이 반환
				
				// byte형 배열 자료를 String으로 변환한다.
//				String msg = new String(bMsg, 0, inpacket.getLength(), "utf-8");
				String msg = new String(inpacket.getData(), 0, inpacket.getLength(), "utf-8");
				
				if("/end".equals(msg)) {
					break;
				}
				
				System.out.println("상대방이 보낸 메시지 : " + msg);
				System.out.println();
				
				// ///////////////////////////////////
				// 상대방에게 메시지 보내기 ( 수신 받은 메시지를 그대로 송신하기)
				
				// 송신할 메시지를 byte형 배열로 변환한다.
				byte[] sendMsg = msg.getBytes("utf-8");
				
				// 송신용 패킷 객체 생성
				// 	==> 전송할 데이터가 저장된 byte형 배열, 전송할 자료의 길이,
				//		상대방의 주소 정보, 포트번호
				//		이렇게 4가지를 지정하여 객체를 생성한다.
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				// 데이터 송신하기 ==> 소켓객체의 send()메서드 이용
				socket.send(outpacket);
				System.out.println("송신 완료...");
				System.out.println();
			} // while문 끝...
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
