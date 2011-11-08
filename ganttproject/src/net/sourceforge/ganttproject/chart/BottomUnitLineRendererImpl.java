/*
GanttProject is an opensource project management tool. License: GPL2
Copyright (C) 2004-2010 Dmitry Barashev

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/
package net.sourceforge.ganttproject.chart;

import java.util.Date;
import java.util.List;

import net.sourceforge.ganttproject.calendar.GPCalendar;
import net.sourceforge.ganttproject.chart.GraphicPrimitiveContainer.Rectangle;
import net.sourceforge.ganttproject.time.TimeUnitText;

/**
 * @author dbarashev (Dmitry Barashev)
 */
public class BottomUnitLineRendererImpl extends ChartRendererBase {
    private GraphicPrimitiveContainer myTimelineContainer;

    public BottomUnitLineRendererImpl(ChartModel model, GraphicPrimitiveContainer primitiveContainer) {
        this(model, primitiveContainer, primitiveContainer);
    }

    public BottomUnitLineRendererImpl(
            ChartModel model,
            GraphicPrimitiveContainer timelineContainer,
            GraphicPrimitiveContainer primitiveContainer) {
        super(model);
        myTimelineContainer = timelineContainer;
    }

    @Override
    public GraphicPrimitiveContainer getPrimitiveContainer() {
        return myTimelineContainer;
    }

    @Override
    public void render() {
        Offset prevOffset = null;
        List<Offset> bottomOffsets = getBottomUnitOffsets();
        int xpos = bottomOffsets.get(0).getOffsetPixels();
        if (xpos > 0) {
            xpos = 0;
        }
        for (Offset offset : bottomOffsets) {
            if (offset.getDayType() == GPCalendar.DayType.WORKING) {
                renderWorkingDay(xpos, offset, prevOffset);
            }
            renderLabel(xpos, offset.getOffsetStart(), offset);
            prevOffset = offset;
            xpos = prevOffset.getOffsetPixels();
        }
    }

    private void renderLabel(int curX, Date curDate, Offset curOffset) {
        TimeUnitText timeUnitText = curOffset.getOffsetUnit().format(curDate);
        String unitText = timeUnitText.getText(-1);
        int posY = getTextBaselinePosition();
        GraphicPrimitiveContainer.Text text = myTimelineContainer.createText(
                curX + 2, posY, unitText);
        myTimelineContainer.bind(text, timeUnitText);
        text.setMaxLength(curOffset.getOffsetPixels() - curX);
        text.setFont(getChartModel().getChartUIConfiguration().getSpanningHeaderFont());
    }
    private void renderWorkingDay(int curX, Offset offset, Offset prevOffset) {
        if (prevOffset != null && prevOffset.getDayType() == GPCalendar.DayType.WORKING) {
            myTimelineContainer.createLine(
                    prevOffset.getOffsetPixels(), getLineTopPosition(),
                    prevOffset.getOffsetPixels(), getLineTopPosition()+10);
        }
    }

    private int getLineTopPosition() {
        return getChartModel().getChartUIConfiguration().getSpanningHeaderHeight();
    }

    private int getLineBottomPosition() {
        return getLineTopPosition() + getLineHeight();
    }

    private int getLineHeight() {
        return getLineTopPosition();
    }

    private int getTextBaselinePosition() {
        return getLineBottomPosition() - 5;
    }

    private List<Offset> getBottomUnitOffsets() {
        return getChartModel().getBottomUnitOffsets();
    }
}