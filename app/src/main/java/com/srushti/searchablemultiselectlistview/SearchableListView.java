package com.srushti.searchablemultiselectlistview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * <h1>com.srushti.searcheablemultiselectlistview</h1>
 *
 * @author Srushti Patel (srushtip@meditab.com) Meditab Software Inc.
 * @since 20/8/18 12:48 PM
 */
public class SearchableListView extends LinearLayout {
    public SearchableListView(Context context) {
        super(context);
    }

    public SearchableListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchableListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SearchableListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
