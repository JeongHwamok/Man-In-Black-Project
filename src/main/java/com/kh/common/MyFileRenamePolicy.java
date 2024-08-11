package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;


// 인터페이스를 상속받아서 구현해야함 => implements 키워드
public class MyFileRenamePolicy implements FileRenamePolicy  {

	// 반드시 미완성된 rename 메소드를 오버라이딩 해서 구현
	// > 기존의 파일을 전달받아서 파일명 수정작업 후
	//   해당 수정된 파일을 반환해주는 메소드
	
	@Override
	public File rename(File originFile) {
		
		// 수정파일명
		// 파일이업로드된시간(년원일시분초) 
		// + 5자리랜덤값(10000~ 99999)
		// + 원본파일의 확장자명
		
		// 원본명				수정명
		// aaa.jpg --->	 2024041512483012345.jpg
		
		// 원본파일명부터 알아내기
		String originName = originFile.getName();
		
		// 1. 파일이업로드된시간 구하기 (년월일시분초)								// 현재 날짜
		String currentTime = new SimpleDateFormat("yyyyMMDDHHmmss").format(new Date());
		
		// 2. 5자리랜덤값(10000~ 99999)
		int ranNum = (int)((Math.random() * 90000) + 10000);
		
		// 3. 원본파일의 확장자 (.jpg, .png,. txt, ..)
		// > 파일명 중간에 .이 들어가는 경우도 있기 때문에
		//   원본파일명으로부터 가장맨 마지막의 . 의 인덱스 기준으로
		//   파일명과 확장자를 나눈다.					// 마지막 .의 위치의 인덱스값 추출
		String ext = originName.substring(originName.lastIndexOf("."));
		
		// 1 + 2 + 3
		String changeName = currentTime + ranNum + ext;
		
		// 원본파일 (originFile) 을
		// 수정된 파일명으로 적용시켜서 파일객체로 변환 후 리턴
		return new File(originFile.getParent(), changeName);
		
	}

	
	
	
	
	
	
	
	
	
}














