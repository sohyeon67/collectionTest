package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
	파일을 전송하는 프로그램 작성하기
		
	- 서버용 프로그램 ==> 서버용 프로그램은 클라이언트와 접속이 성공하고
		클라이언트가 보내온 파일 데이터를 받아서 'd:/d_other/uploads'폴더에 저장한다.
		==> 소켓 스트림으로 읽어서 파일 스트림으로 출력한다.
*/


public class TcpFileServer {
	public void serverStart() {
		File saveDir = new File("d:/d_other/uploads");	// 저장할 폴더 설정
		if(!saveDir.exists()) {	// 저장 폴더가 없으면 새로 만든다.
			saveDir.mkdirs();
		}
		
		Socket socket = null;
		DataInputStream din = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다...");
			
			socket = server.accept();	// 클라이언트의 접속을 기다린다.
			
			System.out.println("파일 수신 시작...");
			
			// 스트림 객체 생성
			din = new DataInputStream(socket.getInputStream());
			
			// 클라이언트가 처음으로 보낸 자료인 파일명을 받는다.
			String filename = din.readUTF();
			
			// 수신한 파일명과 저장할 폴더를 이용한 File객체 생성
			File file = new File(saveDir, filename);
			
			bin = new BufferedInputStream(din);
			bout = new BufferedOutputStream(new FileOutputStream(file));
			
			// 수신한 파일 데이터를 받아서 파일로 저장한다.
			byte[] temp = new byte[1024];
			int len = 0;
			while((len = bin.read(temp)) != -1) {
				bout.write(temp, 0, len);
			}
			bout.flush();
			
			System.out.println("파일 수신 완료...");
			
		} catch (Exception e) {
			System.out.println("파일 수신 실패!!!");
			e.printStackTrace();
		} finally {
			if(din!=null) try { din.close(); } catch(Exception e) {}
			if(bout!=null) try { bout.close(); } catch(Exception e) {}
			if(bin!=null) try { bin.close(); } catch(Exception e) {}
			if(socket!=null) try { socket.close(); } catch(Exception e) {}
			if(server!=null) try { server.close(); } catch(Exception e) {}
		}
	}
	
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
		
	}

}
