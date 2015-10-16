package org.tacademy.basic.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import org.tacademy.basic.calendar.CalendarManager.NoComparableObjectException;

import java.util.ArrayList;

public class SampleCalenarActivity extends Activity {
    /** Called when the activity is first created. */
	TextView titleView;
	GridView gridView;
	CalendarAdapter mAdapter;

	private static final boolean isWeekCalendar = false;

	ArrayList<ItemData> mItemdata = new ArrayList<ItemData>();
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mItemdata.add(new ItemData(2015,9,10,"A"));
        mItemdata.add(new ItemData(2015,9,11,"B"));
        mItemdata.add(new ItemData(2015,9,12,"C"));
        mItemdata.add(new ItemData(2015,9,15,"D"));
        mItemdata.add(new ItemData(2015,9,21,"E"));
        mItemdata.add(new ItemData(2015,9,21,"F"));
        
        titleView = (TextView)findViewById(R.id.title);
        Button btn = (Button)findViewById(R.id.nextMonth);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isWeekCalendar) {
					CalendarData data = CalendarManager.getInstance().getNextWeekCalendarData();
					titleView.setText("" + data.year + " Year " + (data.weekOfYear) + " Week");
					mAdapter.setCalendarData(data);
				} else {
					CalendarData data = CalendarManager.getInstance().getNextMonthCalendarData();
					titleView.setText("" + data.year + " Year " + (data.month + 1) + " Month");
					mAdapter.setCalendarData(data);
				}
			}
		});
        
        btn = (Button)findViewById(R.id.lastMonth);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isWeekCalendar) {
					CalendarData data = CalendarManager.getInstance().getPrevWeekCalendarData();
					titleView.setText("" + data.year + " Year " + (data.weekOfYear) + " Week");
					mAdapter.setCalendarData(data);
				} else {
					CalendarData data = CalendarManager.getInstance().getLastMonthCalendarData();
					titleView.setText("" + data.year + " Year " + (data.month + 1) + " Month");
					mAdapter.setCalendarData(data);
				}
			}
		});
        gridView = (GridView)findViewById(R.id.gridView1);
        try {
			CalendarManager.getInstance().setDataObject(mItemdata);
		} catch (NoComparableObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isWeekCalendar) {
			CalendarData data = CalendarManager.getInstance().getWeekCalendarData();
			titleView.setText("" + data.year + " Year " + (data.weekOfYear) + " Week");
			mAdapter = new CalendarAdapter(this, data);
		} else {
			CalendarData data = CalendarManager.getInstance().getCalendarData();
			titleView.setText("" + data.year + " Year " + (data.month + 1) + " Month");
			mAdapter = new CalendarAdapter(this, data);
		}
		gridView.setAdapter(mAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
									long id) {
				// TODO Auto-generated method stub
				CalendarItem item = (CalendarItem) mAdapter.getItem(position);
				item.items.size();
			}
		});
        
    }
}