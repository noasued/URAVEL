package com.biz;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.dao.TravelDao;
import com.dto.TravelDto;
import com.dao.TravelDwDao;
import com.dto.TravelDetailDto;
import com.dto.TravelListDto;

public class TravelBiz {

	private TravelDao t_dao = new TravelDao();
	TravelDwDao travelDao = new TravelDwDao();

	// 지역구명 호출
	public String selectLocalName(int localcode) {
		return travelDao.selectLocalName(localcode);
	}

	// 테마명 호출
	public String selectThemeName(int themecode) {
		return travelDao.selectThemeName(themecode);
	}

	// 지역별 리스트 호출
	public List<TravelListDto> selectTravelList(int areaListCode) {
		return travelDao.selectTravelList(areaListCode);
	}

	// 테마별 여행지 리스트 호출
	public List<TravelListDto> selectThemeList(int themeListCode) {
		return travelDao.selectThemeList(themeListCode);
	}

	// 여행지 리스트 상세보기
	public TravelDetailDto selectTravelDetail(int travelno) {
		return travelDao.selectTravelDetail(travelno);
	}

	public int selectLikeYn(int travelno, int userno) {
		return travelDao.selectLikeYn(travelno, userno);
	}

	public int insertLike(int travelno, int userno) {
		return travelDao.insertLikeYn(travelno, userno);
	}

	public int deleteLike(int travelno, int userno) {
		return travelDao.deleteLikeYn(travelno, userno);
	}

}
