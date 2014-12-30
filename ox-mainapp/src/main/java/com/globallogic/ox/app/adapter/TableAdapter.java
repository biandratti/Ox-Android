package com.globallogic.ox.app.adapter;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.globallogic.ox.R;
import com.globallogic.ox.app.component.animationFlip.AnimationFactory;
import com.globallogic.ox.app.component.animationFlip.AnimationFactory.FlipDirection;
import com.globallogic.ox.domain.TableRow;
import com.globallogic.ox.domain.ViewHolderStage;

public class TableAdapter extends BaseAdapter {
	
        private Context context;
    
        private List<TableRow> table;
        
        public TableAdapter(Context context, List<TableRow> table) {
                this.context = context;
                this.table = table;
        }
        @Override
        public int getCount() {
                return table.size();
        }
        @Override
        public long getItemId(int position) {
                return position;
        }
        public TableRow getItem(int position) {
                return table.get(position);
        }
        public View getView(int position, View convertView, ViewGroup parent) {
                TableRow tableRow = table.get(position);
                
                LinearLayout layoutTableRowView = new LinearLayout(context);
                layoutTableRowView.setOrientation(LinearLayout.HORIZONTAL);
                
                for (int i = 0; i < tableRow.getSize(); i++) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View layoutCellView = inflater.inflate(R.layout.stage, parent, false);
                	
//                	TableCell tableCell = tableRow.getCellValue(i);
                    //TODO: cambiar este hardcodeo
                	LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(120, LinearLayout.LayoutParams.WRAP_CONTENT);
	    	        
                	ViewHolderStage viewHolder = new ViewHolderStage(context);
	    	        viewHolder.setFlipper((ViewFlipper) layoutCellView.findViewById(R.id.viewFlipper_stage));
	    	        viewHolder.getFlipper().setDisplayedChild(0);
	    	        viewHolder.setButtonRun((Button) layoutCellView.findViewById(R.id.button_stage_run));
	    	        ((ViewGroup) layoutCellView).addView(viewHolder);
	    	        layoutTableRowView.setGravity(Gravity.CENTER);
	    	        layoutTableRowView.addView(layoutCellView, layoutParams);
	    	        
	                final ViewFlipper flipperTemp = (ViewFlipper) layoutCellView.findViewById(R.id.viewFlipper_stage);
	        	    flipperTemp.setOnClickListener(new OnClickListener() {

	        			@Override
	        			public void onClick(View v) {
	        					AnimationFactory.flipTransition(flipperTemp, FlipDirection.LEFT_RIGHT);
	        			}
	        		});
                }
                
    	        return layoutTableRowView;
        }

}