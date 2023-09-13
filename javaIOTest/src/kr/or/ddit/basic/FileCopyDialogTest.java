package kr.or.ddit.basic;

import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
문제)
	'd:/d_other'폴더에 있는 '펭귄.jpg'파일을
	'd:/d_other/연습용'폴더에 '펭귄_복사본.jpg'파일로 
	복사하는 프로그램을 작성하시오.
*/

public class FileCopyDialogTest {

	public static void main(String[] args) {
		FileCopyDialogTest copy = new FileCopyDialogTest();
		
		// 복사할 원본 파일 선택하기
//		File file = new File("d:/d_other/펭귄.jpg");
		File file = copy.showDialog("open");
		
		if(file==null) {
			System.out.println("원본 파일을 선택하지 않았습니다.");
			System.out.println("복사 작업을 중단합니다...");
			return;
		}
		
		if(!file.exists()) {
			System.out.println(file.getPath() + " 파일이 없습니다...");
			System.out.println("복사 작업을 중단합니다...");
			return;
		}
		
		try {
			// 입력용 스트림과 출력용 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);
			
			// 복사될 대상 파일 선택하기
			File targetFile = copy.showDialog("save");
			
			if(targetFile==null) {
				System.out.println("복사할 대상 파일을 선택하지 않았습니다.");
				System.out.println("복사 작업을 중단합니다...");
				return;
			}
			
			FileOutputStream fout = 
//					new FileOutputStream("d:/d_other/연습용/펭귄_복사본.jpg");
					new FileOutputStream(targetFile); 
			
			BufferedInputStream bin = new BufferedInputStream(fin);
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			
			System.out.println("복사 시작...");

/*
			int data;		// 읽어온 데이터가 저장될 변수
			
//			while( (data = fin.read()) != -1 ) {
//				fout.write(data);
//			}
//			fout.close();
//			fin.close();
			
			while( (data = bin.read()) != -1 ) {
				bout.write(data);
			}
			
*/			
			// 배열을 이용한 입출력 작업
			byte[] temp = new byte[1024];
			int len = 0;
			while( (len = bin.read(temp)) != -1 ) {
				bout.write(temp, 0, len);
			}
			
			bout.flush();
			
			bout.close();
			bin.close();
			
			System.out.println("복사 작업 완료...");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// Dialog창을 나타내고 선택한 파일을 반환하는 메서드
	// option이 'open'이면 열기창, 'save'면 저장창을 나타낸다.
	public File showDialog(String option) {
		JFileChooser chooser = new JFileChooser();
		
		// 선택할 파일의 종류 설정하기 (파일의 확장자를 이용하여 구별한다.)
		FileNameExtensionFilter txt =
				new FileNameExtensionFilter("Text파일(*.txt)", "txt");
		FileNameExtensionFilter img =
				new FileNameExtensionFilter("그림파일", "png", "jpg", "gif");	// 가변형
		FileNameExtensionFilter doc =
				new FileNameExtensionFilter("MS Word파일", new String[] {"doc", "docx"});	// 배열
		
		chooser.addChoosableFileFilter(txt);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(doc);
		
		// '모든 파일'이라는 항목의 표시 여부 설정 (true:설정, false:해제)
		// (기본값: true)
		chooser.setAcceptAllFileFilterUsed(true);
//		chooser.setAcceptAllFileFilterUsed(false);
		
		// Dialog창에 나타날 기본 경로 설정 ('d:/d_other'로 설정하기)
		chooser.setCurrentDirectory(new File("d:/d_other"));
		
		int result;
		
		if("save".equals(option.toLowerCase())) {
			result = chooser.showSaveDialog(new Panel());	// 저장용
		} else if("open".equals(option.toLowerCase())) {
			result = chooser.showOpenDialog(new Panel());	// 열기용
		} else {
			System.out.println("option변수에는 save 또는 open만 가능합니다.");
			return null;
		}
		
		
		File selectedFile = null;
		// 창에서 '저장' 또는 '열기'버튼을 눌렀을 때를 검사한다.
		if(result == JFileChooser.APPROVE_OPTION) {	
			selectedFile = chooser.getSelectedFile();
		}
		return selectedFile;
	}

}
