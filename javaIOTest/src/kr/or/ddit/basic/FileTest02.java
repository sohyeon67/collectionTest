package kr.or.ddit.basic;

import java.io.File;

public class FileTest02 {

	public static void main(String[] args) {
		File f1 = new File("d:/d_other/test.txt");
		System.out.println(f1.getName() + " 파일의 크기 : " + f1.length() + "bytes");
		System.out.println("path : " + f1.getPath());
		System.out.println("absolutePath : " + f1.getAbsolutePath());
		System.out.println();
		
		// 이클립스에서 자바 프로그램이 실행되는 위치는 해당 프로그램이 위치하는
		// 프로젝트를 말한다. ( 즉, '프로젝트이름'에 해당하는 폴더가 된다.)
		File f2 = new File(".");
		System.out.println("path : " + f2.getPath());
		System.out.println("absolutePath : " + f2.getAbsolutePath());
		System.out.println();
		
		
		
		if(f1.isFile()) {
			System.out.println(f1.getName() + " 은(는) 파일입니다.");
		} else if(f1.isDirectory()) {
			System.out.println(f1.getName() + " 은(는) 디렉토리입니다.");
		} else {
			System.out.println(f1.getName() + " 은(는) 뭘까???");
		}
		System.out.println();
		
		if(f2.isFile()) {
			System.out.println(f2.getName() + " 은(는) 파일입니다.");
		} else if(f2.isDirectory()) {
			System.out.println(f2.getName() + " 은(는) 디렉토리입니다.");
		} else {
			System.out.println(f2.getName() + " 은(는) 뭘까???");
		}
		System.out.println();
		
		// 현재 디스크에 존재하지 않는 파일 정보를 갖는 File객체는
		// 파일 또는 디렉토리 여부를 검사하지 못한다.
		File f3 = new File("d:/d_other/sample.exe");
		if(f3.isFile()) {
			System.out.println(f3.getName() + " 은(는) 파일입니다.");
		} else if(f3.isDirectory()) {
			System.out.println(f3.getName() + " 은(는) 디렉토리입니다.");
		} else {
			System.out.println(f3.getName() + " 은(는) 뭘까???");
		}
	}

}
