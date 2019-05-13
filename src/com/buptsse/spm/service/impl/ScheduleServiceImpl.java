package com.buptsse.spm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.buptsse.spm.dao.IScheduleDao;
import com.buptsse.spm.domain.Course;
import com.buptsse.spm.domain.Schedule;
import com.buptsse.spm.service.IScheduleService;


/**
 * @author BUPT-TC  
 * @date 2015年11月16日 下午3:53
 * @description 视频进度的service层实现类定义 
 * @modify BUPT-TC 
 * @modifyDate 
 */

@Transactional
@Service
public class ScheduleServiceImpl implements IScheduleService{

	@Resource
	private IScheduleDao iScheduleDao;



	@Override
	public Schedule findScheduleById(String id) {
		// TODO Auto-generated method stub
		return iScheduleDao.findScheduleById(new Integer(id));
	}

	@Override
	public boolean insertSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		return iScheduleDao.saveSchedule(schedule);
	}

	@Override
	public List<Schedule> findAllSchedule() {
		// TODO Auto-generated method stub
		String hql = "from Schedule";
		List list = new ArrayList();
		return iScheduleDao.findSchedule(hql, list);
	}

	@Override
	public List<Schedule> findScheduleByUserIdAndStepOrder(Integer stepOrder,String userId) {
		// TODO Auto-generated method stub
		
		String hql = "from Schedule where userId=? and video_step_order=?";
		List listParam = new ArrayList();
		listParam.add(userId);
		listParam.add(stepOrder);
		return iScheduleDao.findSchedule(hql, listParam);		
	}	
	
	@Override
	public boolean deleteSchedule(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOrUpdate(Schedule schedule) {
		// TODO Auto-generated method stub
		//List<Schedule> list = this.findScheduleByUserIdAndStepOrder(schedule.getVideo_step_order(), schedule.getUserid());
		
		//if(list!=null && list.size()>0){
		//	Schedule scheduleTmp = list.get(0);
		//	scheduleTmp.setPercent(schedule.getPercent());
		//}
		//return false;
		
		return iScheduleDao.saveOrUpdateSchedule(schedule);
	}
	

	@Override
	public List<Schedule> findScheduleByUserIdAndChapterId(Integer chapterId,
			String userId) {
		// TODO Auto-generated method stub
		String hql = "from Schedule where userId=? and chapter_id=?";
		List listParam = new ArrayList();
		listParam.add(userId);
		listParam.add(chapterId);
		return iScheduleDao.findSchedule(hql, listParam);			
		
	}
	
	
	
	
	
	
	public IScheduleDao getiScheduleDao() {
		return iScheduleDao;
	}

	public void setiScheduleDao(IScheduleDao iScheduleDao) {
		this.iScheduleDao = iScheduleDao;
	}
	
	
	public double countVideoGrade(String userId) {
		String hql = "SELECT sum(percent)/count(*) FROM schedule where userId ="+userId;

		System.out.println(hql);
		List res = iScheduleDao.find(hql);
		double a = ((BigDecimal) (res.get(0))).doubleValue();
		return a;
	}

	@Override
	public double countExamGrade(String userId) {
		String hql = "select totalGrade from course where studentId ="+userId;

		System.out.println(hql);
		List res = iScheduleDao.find(hql);
		double a = ((BigDecimal) (res.get(0))).doubleValue();
		return a;
	}

	@Override
	public List<Double> countMaxMinAverTimeGrade() {
		String hqlmax = "select Max(videoTime) from user";
		String hqlmin = "select Min(videoTime) from user";
		String hqlaver = "select AVG(videoTime) from user";
		System.out.println(hqlmax);
		System.out.println(hqlmin);
		System.out.println(hqlaver);
		List maxres = iScheduleDao.find(hqlmax);
		List minres = iScheduleDao.find(hqlmin);
		List averres = iScheduleDao.find(hqlaver);
		double max = ((Integer) maxres.get(0)).doubleValue();
		double min = ((Integer) minres.get(0)).doubleValue();
		double aver = ((BigDecimal) averres.get(0)).doubleValue();
		List<Double> res = new ArrayList();
		res.add(max);
		res.add(min);
		res.add(aver);
		return res;
	}
	
	@Override
	public double getOnlineTime(String userId) {
		String hql = "select videoTime from user where userId ="+userId;
		System.out.println(hql);
		List res = iScheduleDao.find(hql);
		double a = ((Integer) res.get(0)).doubleValue();
		return a;
		
	}
	
	
	;






}
