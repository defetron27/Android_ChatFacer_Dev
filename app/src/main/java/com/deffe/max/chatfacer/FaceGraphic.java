package com.deffe.max.chatfacer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class FaceGraphic extends GraphicOverlay.Graphic
{
    private volatile FaceData mFaceData;

    private Drawable mJokerMask;




    FaceGraphic(GraphicOverlay overlay, Context context, boolean isFrontFacing)
    {
        super(overlay);
        Resources resources = context.getResources();
        prepareMasks(resources);
    }

    private void prepareMasks(Resources resources)
    {

    }

    private void createThumbnails(ArrayList<Drawable> masks)
    {

    }

    void update(FaceData faceData)
    {
        mFaceData = faceData;

        postInvalidate();
    }

    @Override
    public void draw(Canvas canvas)
    {
        FaceData faceData = mFaceData;
        if (faceData == null)
        {
            return;
        }
        PointF detectPosition = faceData.getPosition();
        PointF detectLeftEyePosition = faceData.getLeftEyePosition();
        PointF detectRightEyePosition = faceData.getRightEyePosition();
        PointF detectNoseBasePosition = faceData.getNoseBasePosition();

        if ((detectPosition == null) ||
                (detectLeftEyePosition == null) ||
                (detectRightEyePosition == null) ||
                (detectNoseBasePosition == null))
        {
            return;
        }

        PointF facePosition = new PointF(translateX(detectPosition.x), translateY(detectPosition.y));

        float width = scaleX(faceData.getWidth());
        float height = scaleY(faceData.getHeight());

        // Eye coordinates
        PointF leftEyePosition = new PointF(translateX(detectLeftEyePosition.x), translateY(detectLeftEyePosition.y));
        PointF rightEyePosition = new PointF(translateX(detectRightEyePosition.x), translateY(detectRightEyePosition.y));

        // Nose coordinates
        PointF noseBasePosition = new PointF(translateX(detectNoseBasePosition.x), translateY(detectNoseBasePosition.y));

        drawJokerMask(canvas, noseBasePosition, width, height);
    }

    private void drawJokerMask(Canvas canvas, PointF noseBasePosition,float faceWidth,float faceHeight)
    {
        int left = (int)(noseBasePosition.x - (faceWidth /2));
        int top = (int)(noseBasePosition.y - (faceHeight /2));

        int right = (int)(noseBasePosition.x + (faceWidth /2));
        int bottom = (int)(noseBasePosition.y + (faceHeight /2));

        mJokerMask.setBounds(left, top, right, bottom);
        mJokerMask.draw(canvas);
    }
}

