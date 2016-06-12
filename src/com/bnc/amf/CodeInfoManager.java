package com.bnc.amf;

import java.util.List;

import com.bnc.common.model.CodeInfoDTO;
import com.bnc.props.CodeProperties;

public class CodeInfoManager
{
	// 차량 등급 코드 리스트
	private List<CodeInfoDTO> codeCarGradeList;
	
	public List<CodeInfoDTO> getCodeCarGradeList()
	{
		System.out.println( "Flex Call : " + codeCarGradeList.get( 0 ).getName() );
		return codeCarGradeList;
	}

	public String getCodeName( int codeType, int code )
	{
		String codeName = CodeProperties.getCodeName( codeType, code );
		System.out.println( "Flex Call CodeName : " + codeName );
		
		return codeName;
	}
	
	public String setFlexMessage( String message )
	{
		System.out.println( "Flex Message : " + message );
		return message;
	}
}
