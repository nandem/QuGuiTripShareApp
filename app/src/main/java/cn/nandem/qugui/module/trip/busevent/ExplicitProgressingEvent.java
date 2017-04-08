package cn.nandem.qugui.module.trip.busevent;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author Nandem on 17-4-8.
 */

public class ExplicitProgressingEvent
{
    private SupportFragment supportFragment;

    public ExplicitProgressingEvent(SupportFragment supportFragment)
    {
        this.supportFragment = supportFragment;
    }

    public SupportFragment getSupportFragment()
    {
        return supportFragment;
    }
}
