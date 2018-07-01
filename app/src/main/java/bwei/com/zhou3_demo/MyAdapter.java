package bwei.com.zhou3_demo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bwei.com.zhou3_demo.myapp.MyApp;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   private List<MainBean.DataBeanX.DataBean> list;
   private static final int ONE=0;
   private static final int TWO=1;

    private OnItemClickListener onItemClickListener;

    public MyAdapter(List<MainBean.DataBeanX.DataBean> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getPics().size()==3){
            return ONE;
        }else{
            return TWO;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if (viewType==ONE){
           Log.d("11111","aaaa");
           View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_two, parent, false);
           return new myViewHolder1(view1);
       }else{
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_layout, parent, false);
            return new myViewHolder(itemView);
       }

    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof myViewHolder){
            final myViewHolder  holder1= (myViewHolder) holder;
            holder1.tv.setText(list.get(position).getTitle());
            holder1.tv2.setText(list.get(position).getTitle());
            ImageLoader.getInstance().displayImage("http://365jia.cn/uploads/"+list.get(position).getPics().get(0),holder1.image,MyApp.getOptions());
            holder1.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("一张","posin++++++++"+position);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(holder1.image, "alpha", 1f,0f,1f);
                    objectAnimator.setDuration(5000);

                    objectAnimator.start();
                }
            });
            holder1.mitemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemClickListener!=null){
                        onItemClickListener.onItemClick(v,position);
                    }
                    return true;
                }
            });
        }else{
            final myViewHolder1  holder2 = (myViewHolder1) holder;
            ImageLoader.getInstance().displayImage("http://365jia.cn/uploads/"+list.get(position).getPics().get(0),holder2.imag1, MyApp.getOptions());
            ImageLoader.getInstance().displayImage("http://365jia.cn/uploads/"+list.get(position).getPics().get(1),holder2.imag2, MyApp.getOptions());
            ImageLoader.getInstance().displayImage("http://365jia.cn/uploads/"+list.get(position).getPics().get(2),holder2.imag3, MyApp.getOptions());
            holder2.imag1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("第二张","posin++++++++"+position);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(holder2.imag1, "alpha", 1f,0f,1f);
                    objectAnimator.setDuration(5000);
                    objectAnimator.start();
                }
            });
            holder2.imag2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("第三张","posin++++++++"+position);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(holder2.imag2, "alpha", 1f,0f,1f);
                    objectAnimator.setDuration(5000);
                    objectAnimator.start();
                }
            });
            holder2.imag3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("第四张","posin++++++++"+position);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(holder2.imag3, "alpha", 1f,0f,1f);
                    objectAnimator.setDuration(5000);
                    objectAnimator.start();
                }
            });
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(v,position);
                }
                return false;
            }
        });
    }
    @Override
    public int getItemCount() {
        return list==null ? 0 :list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv,tv2;
        private final ImageView image;
        private View mitemView;
        public myViewHolder(final View itemView) {
            super(itemView);
            mitemView = itemView;
            tv = itemView.findViewById(R.id.itme_tv);
            tv2 = itemView.findViewById(R.id.itme_tv2);
            image = itemView.findViewById(R.id.itme_image);
        }
    }

    private class myViewHolder1 extends RecyclerView.ViewHolder {
        private ImageView imag1,imag2,imag3;
        public myViewHolder1(View view1) {
            super(view1);
            imag1 = view1.findViewById(R.id.itme_image0);
            imag2 = view1.findViewById(R.id.itme_image2);
            imag3 = view1.findViewById(R.id.itme_image3);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListtener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
