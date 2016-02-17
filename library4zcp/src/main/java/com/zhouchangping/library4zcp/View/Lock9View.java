//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zhouchangping.library4zcp.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.zhouchangping.library4zcp.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lock9View extends ViewGroup {
    private List<Pair<NodeView, NodeView>> lineList = new ArrayList();
    private Lock9View.NodeView currentNode;
    private float x;
    private float y;
    private Drawable nodeSrc;
    private Drawable nodeOnSrc;
    private float nodeSize;
    private float nodeAreaExpand;
    private int nodeOnAnim;
    private int lineColor;
    private float lineWidth;
    private float padding;
    private float spacing;
    private Vibrator vibrator;
    private boolean enableVibrate;
    private int vibrateTime;
    private Paint paint;
    private StringBuilder passwordBuilder = new StringBuilder();
    private Lock9View.CallBack callBack;

    public void setCallBack(Lock9View.CallBack callBack) {
        this.callBack = callBack;
    }

    public Lock9View(Context context) {
        super(context);
        this.initFromAttributes(context, (AttributeSet)null, 0);
    }

    public Lock9View(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initFromAttributes(context, attrs, 0);
    }

    public Lock9View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initFromAttributes(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public Lock9View(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.initFromAttributes(context, attrs, defStyleAttr);
    }

    private void initFromAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.Lock9View, defStyleAttr, 0);
        this.nodeSrc = a.getDrawable(R.styleable.Lock9View_lock9_nodeSrc);
        this.nodeOnSrc = a.getDrawable(R.styleable.Lock9View_lock9_nodeOnSrc);
        this.nodeSize = a.getDimension(R.styleable.Lock9View_lock9_nodeSize, 0.0F);
        this.nodeAreaExpand = a.getDimension(R.styleable.Lock9View_lock9_nodeAreaExpand, 0.0F);
        this.nodeOnAnim = a.getResourceId(R.styleable.Lock9View_lock9_nodeOnAnim, 0);
        this.lineColor = a.getColor(R.styleable.Lock9View_lock9_lineColor, Color.argb(0, 0, 0, 0));
        this.lineWidth = a.getDimension(R.styleable.Lock9View_lock9_lineWidth, 0.0F);
        this.padding = a.getDimension(R.styleable.Lock9View_lock9_padding, 0.0F);
        this.spacing = a.getDimension(R.styleable.Lock9View_lock9_spacing, 0.0F);
        this.enableVibrate = a.getBoolean(R.styleable.Lock9View_lock9_enableVibrate, false);
        this.vibrateTime = a.getInt(R.styleable.Lock9View_lock9_vibrateTime, 20);
        a.recycle();
        if(this.enableVibrate) {
            this.vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        }

        this.paint = new Paint(4);
        this.paint.setStyle(Style.STROKE);
        this.paint.setStrokeWidth(this.lineWidth);
        this.paint.setColor(this.lineColor);
        this.paint.setAntiAlias(true);

        for(int n = 0; n < 9; ++n) {
            Lock9View.NodeView node = new Lock9View.NodeView(this.getContext(), n + 1);
            this.addView(node);
        }

        this.setWillNotDraw(false);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = this.measureSize(widthMeasureSpec);
        this.setMeasuredDimension(size, size);
    }

    private int measureSize(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch(specMode) {
        case -2147483648:
        case 1073741824:
            return specSize;
        default:
            return 0;
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if(changed) {
            float nodeSize;
            int n;
            Lock9View.NodeView node;
            int row;
            int col;
            int l;
            int t;
            int r;
            int b;
            if(this.nodeSize > 0.0F) {
                nodeSize = (float)((right - left) / 3);

                for(n = 0; n < 9; ++n) {
                    node = (Lock9View.NodeView)this.getChildAt(n);
                    row = n / 3;
                    col = n % 3;
                    l = (int)((float)col * nodeSize + (nodeSize - this.nodeSize) / 2.0F);
                    t = (int)((float)row * nodeSize + (nodeSize - this.nodeSize) / 2.0F);
                    r = (int)((float)l + this.nodeSize);
                    b = (int)((float)t + this.nodeSize);
                    node.layout(l, t, r, b);
                }
            } else {
                nodeSize = ((float)(right - left) - this.padding * 2.0F - this.spacing * 2.0F) / 3.0F;

                for(n = 0; n < 9; ++n) {
                    node = (Lock9View.NodeView)this.getChildAt(n);
                    row = n / 3;
                    col = n % 3;
                    l = (int)(this.padding + (float)col * (nodeSize + this.spacing));
                    t = (int)(this.padding + (float)row * (nodeSize + this.spacing));
                    r = (int)((float)l + nodeSize);
                    b = (int)((float)t + nodeSize);
                    node.layout(l, t, r, b);
                }
            }
        }

    }

    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
        case 0:
        case 2:
            this.x = event.getX();
            this.y = event.getY();
            Lock9View.NodeView nodeAt = this.getNodeAt(this.x, this.y);
            if(this.currentNode == null) {
                if(nodeAt != null) {
                    this.currentNode = nodeAt;
                    this.currentNode.setHighLighted(true);
                    this.passwordBuilder.append(this.currentNode.getNum());
                    this.invalidate();
                }
            } else {
                if(nodeAt != null && !nodeAt.isHighLighted()) {
                    nodeAt.setHighLighted(true);
                    Pair var5 = new Pair(this.currentNode, nodeAt);
                    this.lineList.add(var5);
                    this.currentNode = nodeAt;
                    this.passwordBuilder.append(this.currentNode.getNum());
                }

                this.invalidate();
            }
            break;
        case 1:
            if(this.passwordBuilder.length() > 0) {
                if(this.callBack != null) {
                    this.callBack.onFinish(this.passwordBuilder.toString());
                }

                this.lineList.clear();
                this.currentNode = null;
                this.passwordBuilder.setLength(0);

                for(int n = 0; n < this.getChildCount(); ++n) {
                    Lock9View.NodeView node = (Lock9View.NodeView)this.getChildAt(n);
                    node.setHighLighted(false);
                }

                this.invalidate();
            }
        }

        return true;
    }

    protected void onDraw(Canvas canvas) {
        Iterator var2 = this.lineList.iterator();

        while(var2.hasNext()) {
            Pair pair = (Pair)var2.next();
            canvas.drawLine((float)((Lock9View.NodeView)pair.first).getCenterX(), (float)((Lock9View.NodeView)pair.first).getCenterY(), (float)((Lock9View.NodeView)pair.second).getCenterX(), (float)((Lock9View.NodeView)pair.second).getCenterY(), this.paint);
        }

        if(this.currentNode != null) {
            canvas.drawLine((float)this.currentNode.getCenterX(), (float)this.currentNode.getCenterY(), this.x, this.y, this.paint);
        }

    }

    private Lock9View.NodeView getNodeAt(float x, float y) {
        for(int n = 0; n < this.getChildCount(); ++n) {
            Lock9View.NodeView node = (Lock9View.NodeView)this.getChildAt(n);
            if(x >= (float)node.getLeft() - this.nodeAreaExpand && x < (float)node.getRight() + this.nodeAreaExpand && y >= (float)node.getTop() - this.nodeAreaExpand && y < (float)node.getBottom() + this.nodeAreaExpand) {
                return node;
            }
        }

        return null;
    }

    private class NodeView extends View {
        private int num;
        private boolean highLighted = false;

        public NodeView(Context context, int num) {
            super(context);
            this.num = num;
            this.setBackgroundDrawable(Lock9View.this.nodeSrc);
        }

        public boolean isHighLighted() {
            return this.highLighted;
        }

        public void setHighLighted(boolean highLighted) {
            if(this.highLighted != highLighted) {
                this.highLighted = highLighted;
                if(Lock9View.this.nodeOnSrc != null) {
                    this.setBackgroundDrawable(highLighted?Lock9View.this.nodeOnSrc:Lock9View.this.nodeSrc);
                }

                if(Lock9View.this.nodeOnAnim != 0) {
                    if(highLighted) {
                        this.startAnimation(AnimationUtils.loadAnimation(this.getContext(), Lock9View.this.nodeOnAnim));
                    } else {
                        this.clearAnimation();
                    }
                }

                if(Lock9View.this.enableVibrate && highLighted) {
                    Lock9View.this.vibrator.vibrate((long)Lock9View.this.vibrateTime);
                }
            }

        }

        public int getCenterX() {
            return (this.getLeft() + this.getRight()) / 2;
        }

        public int getCenterY() {
            return (this.getTop() + this.getBottom()) / 2;
        }

        public int getNum() {
            return this.num;
        }
    }

    public interface CallBack {
        void onFinish(String var1);
    }
}
