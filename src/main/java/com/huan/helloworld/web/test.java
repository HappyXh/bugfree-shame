package com.huan.helloworld.web;


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * Created by happy on 7/25/2015.
 */



public class test {
    private static ActiveXComponent ppt;
    private static ActiveXComponent presentation;

    public static void main(String[] args) {
        ppt = new ActiveXComponent("PowerPoint.Application");
        ppt.setProperty("Visible", new Variant(true));


        new test().PPTToJPG("D:\\Project\\Pointhinker\\resource\\resume\\resume.ppt","D:\\Project\\Pointhinker\\resource\\resume");

    }

    public void PPTToJPG(String pptfile, String saveToFolder) {
        try {
            ActiveXComponent presentations = ppt.getPropertyAsComponent("Presentations");
            presentation = presentations.invokeGetComponent("Open", new Variant(pptfile), new Variant(true));
            saveAs(presentation, saveToFolder);
            if (presentation != null) {
                Dispatch.call(presentation, "Close");
            }
        } catch (Exception e) {
            ComThread.Release();
        } finally {
            ppt.invoke("Quit", new Variant[] {});
            ComThread.Release();
        }
    }
    public void saveAs(ActiveXComponent presentation, String saveTo)throws Exception {
        ActiveXComponent  mySlides=presentation.getPropertyAsComponent("Slides");
        int num_slide=mySlides.getPropertyAsInt("Count");
        for(int i=1;i<=num_slide;i++) {
            Dispatch pptPage = Dispatch.call(mySlides, "Item", new Object[]{new Variant(i)}).toDispatch();
            Dispatch.call(pptPage,"Export","D:\\Project\\Pointhinker\\resource\\resume\\resume"+i+".PNG","PNG");
        }

    }
}