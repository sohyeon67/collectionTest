package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {

	public static void main(String[] args) {
		// AWT ==> SWING ==> javaFX
		
		// SWING의 파일 열기, 저장 창 연습
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
		
//		int result = chooser.showOpenDialog(new Panel());	// 열기용
		int result = chooser.showSaveDialog(new Panel());	// 저장용
		
		// 창에서 '저장' 또는 '열기'버튼을 눌렀을 때를 검사한다.
		if(result == JFileChooser.APPROVE_OPTION) {	
			File selectedFile = chooser.getSelectedFile();
			System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath());
		}
	}

}
