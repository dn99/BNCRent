package com.bnc.util;

public class PageNavi
{
	boolean nowFirst = false;
	boolean nowEnd = false;

	int totalArticle = 0;
	int totalPage = 0;
	int pageNo = 0;

	/**
	 * 리스트로 이동시 호출할 자바스크립트 메서드명
	 */
	private String callFunction;
	/**
	 * 페이지 사이즈를 디폴트(PageSize.PAGE_SIZE)를 사용하지 않을때 값 입력 
	 */
	private int pageSize;
	
	public String getCallFunction()
	{
		return callFunction;
	}
	public void setCallFunction( String callFunction )
	{
		this.callFunction = callFunction;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public void setPageSize( int pageSize )
	{
		this.pageSize = pageSize;
	}
	
	public int No()
	{
		return pageNo;
	}

	public void setPageNo( int pageNo )
	{
		this.pageNo = pageNo;
	}

	public int getTotalArticle()
	{
		return totalArticle;
	}

	public void setTotalArticle( int totalArticle )
	{
		this.totalArticle = totalArticle;
	}

	public int getTotalPage()
	{
		return totalPage;
	}

	public void setTotalPage( int totalPage )
	{
		this.totalPage = totalPage;
	}

	public boolean isNowEnd()
	{
		return nowEnd;
	}

	public void setNowEnd( boolean nowEnd )
	{
		this.nowEnd = nowEnd;
	}

	public boolean isNowFirst()
	{
		return nowFirst;
	}

	public void setNowFirst( boolean nowFirst )
	{
		this.nowFirst = nowFirst;
	}

	public StringBuffer pageNavi()
	{
		// 디폴트 페이지 사이즈 설정(PageSize.PAGE_SIZE)
		if ( pageSize == 0 ) pageSize = PageSize.PAGE_SIZE;
		
		int iStartPage = ( ( pageNo - 1 ) / pageSize ) * pageSize + 1;
		int iEndPage = iStartPage + pageSize - 1;

		if ( iEndPage > totalPage ) iEndPage = totalPage;
		if ( pageNo > iEndPage ) pageNo = iEndPage;
		
		 System.out
		 .println("====================================================");
		 System.out.println("pageNo ==" + pageNo);
		 System.out.println("iStartPage == " + iStartPage);
		 System.out.println("iEndPage == " + iEndPage);
		 System.out.println("isNowFirst == " + isNowFirst());
		 System.out.println("isNowEnd == " + isNowEnd());
		 System.out
		 .println("====================================================");

		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append( "<table cellpadding='0' cellspacing='0' border='0'>\n" );
		sbHtml.append( " <tr>\n" );
		if ( this.isNowFirst() )
		{
			sbHtml.append( "  <td><font color='#999999'>\n" );
			sbHtml.append( "   <img src='/BNCRent/images/board/icon_prev02.gif' width='7' height='11' border='0' align='absmiddle' hspace='3'>최신목록\n" );
			sbHtml.append( "   <img src='/BNCRent/images/board/icon_prev01_dim.gif' width='3' height='11' border='0' align='absmiddle' hspace='3'>\n" );
			sbHtml.append( "   이전</font>\n" );
		} 
		else
		{
			sbHtml.append( "  <td>\n<a href='javascript:" + callFunction +"(1)'>" );
			sbHtml.append( "   <img src='/BNCRent/images/board/icon_prev02.gif' width='7' height='11' border='0' align='absmiddle' hspace='3'>최신목록</a>\n" );
			sbHtml.append( "   <a href='javascript:" + callFunction +"("
					+ ( ( pageNo - 1 ) / pageSize * pageSize ) + ")'>" );
			sbHtml.append( "   <img src='/BNCRent/images/board/icon_prev01_dim.gif' width='3' height='11' border='0' align='absmiddle' hspace='3'>\n" );
			sbHtml.append( "   이전</a>" );
		}
		sbHtml.append( "  \n</td>\n" );
		sbHtml.append( "  <td style='padding: 0 5 0 5'>\n" );
		sbHtml.append( "   <table cellpadding='0' cellspacing='0' border='0'>\n" );
		sbHtml.append( "    <tr>\n" );
		sbHtml.append( "     <td width='1' nowrap><img src='/BNCRent/images/board/n_tab.gif' width='1'" );
		sbHtml.append( " height='11' border='0' align='absmiddle'><br>" );
		sbHtml.append( "     </td>\n" );
		for ( int i = iStartPage; i <= iEndPage; i++ )
		{
			if ( pageNo == i )
			{
				sbHtml.append( "     <td style='padding:0 7 0 7;' nowrap><font class='text_acc_02'><b>"
						+ i + "</b></font></td>\n" );
				sbHtml.append( "     <td width='1' nowrap><img src='/BNCRent/images/board/n_tab.gif' width='1'" );
				sbHtml.append( " height='11' border='0' align='absmiddle'><br>\n" );
			} else
			{
				sbHtml.append( "     <td style='padding:0 7 0 7;' nowrap><a href='javascript:" + callFunction +"("
						+ i + ")'>" + i + "</td>\n" );
				sbHtml.append( "     <td width='1' nowrap><img src='/BNCRent/images/board/n_tab.gif' width='1'" );
				sbHtml.append( " height='11' border='0' align='absmiddle'><br>\n" );
			}
		}
		sbHtml.append( "     </td>\n" );
		sbHtml.append( "    </tr>\n" );
		sbHtml.append( "   </table>\n" );
		sbHtml.append( "  </td>\n" );
		sbHtml.append( "  <td>\n" );
		if ( this.isNowEnd() )
		{
			sbHtml.append( "   <font color='#999999'>다음<img" );
			sbHtml.append( "   src='/BNCRent/images/board/icon_next01_dim.gif' width='3' height='11'" );
			sbHtml.append( " border='0' align='absmiddle' hspace='3'> \n" );
			sbHtml.append( "   끝목록<img src='/BNCRent/images/board/icon_next02_dim.gif' width='7' height='11'" );
			sbHtml.append( " border='0' align='absmiddle' hspace='3'></font>\n" );
		} else
		{
			sbHtml.append( "   <a href='javascript:" + callFunction +"("
					+ ( ( pageNo + ( pageSize - 1 ) ) / pageSize * pageSize + 1 ) + ")'>다음<img" );
			sbHtml.append( " src='/BNCRent/images/board/icon_next01_dim.gif' width='3' height='11'" );
			sbHtml.append( " border='0' align='absmiddle' hspace='3'></a>\n" );
			sbHtml.append( "   <a href='javascript:" + callFunction +"("
					+ totalPage
					+ ")'>끝목록<img src='/BNCRent/images/board/icon_next02_dim.gif' width='7' height='11'" );
			sbHtml.append( " border='0' align='absmiddle' hspace='3'>\n" );
		}

		sbHtml.append( "  </td>\n" );
		sbHtml.append( " </tr>\n" );
		sbHtml.append( "</table>\n" );

		return sbHtml;

	}

}
