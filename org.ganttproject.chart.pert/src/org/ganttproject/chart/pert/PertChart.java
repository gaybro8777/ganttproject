/***************************************************************************
PertChart.java - description
Copyright [2005 - ADAE]
This file is part of GanttProject].
***************************************************************************/

/***************************************************************************
 * GanttProject is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License as published by    *
 * the Free Software Foundation; either version 2 of the License, or       *
 * (at your option) any later version.                                     *
 *                                                                         *
 * GanttProject is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the           *
 * GNU General Public License for more details.                            *

***************************************************************************/

package org.ganttproject.chart.pert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import net.sourceforge.ganttproject.IGanttProject;
import net.sourceforge.ganttproject.chart.Chart;
import net.sourceforge.ganttproject.chart.ChartSelection;
import net.sourceforge.ganttproject.chart.ChartSelectionListener;
import net.sourceforge.ganttproject.gui.options.model.GPOptionGroup;
import net.sourceforge.ganttproject.task.TaskManager;

import org.eclipse.core.runtime.IStatus;

/**
 * Abstract class that should implement all PERT chart implementation.
 *
 * @author bbaranne
 */
public abstract class PertChart extends JPanel implements Chart {

    // TODO List of Listeners is not used...
    private final List<ChartSelectionListener> myListeners = new ArrayList<ChartSelectionListener>();

    /**
     * Task manager used to build PERT chart. It provides data.
     */
    protected TaskManager myTaskManager;

    public PertChart() {
    }

    public void init(IGanttProject project) {
        myTaskManager = project.getTaskManager();
    }
    /**
     * @inheritDoc
     */
    @Override
    public abstract String getName();

    /**
     * Builds PERT chart.
     *
     */
    protected abstract void buildPertChart();

    /**
     * This method in not supported by this Chart.
     */
    public Date getStartDate() {
        throw new UnsupportedOperationException();
    }

    /**
     * This method in not supported by this Chart.
     */
    public Date getEndDate() {
        throw new UnsupportedOperationException();
    }

    public GPOptionGroup[] getOptionGroups() {
        return null;
    }

    public Chart createCopy() {
        return null;
    }

    public ChartSelection getSelection() {
        throw new UnsupportedOperationException();
    }

    public IStatus canPaste(ChartSelection selection) {
        throw new UnsupportedOperationException();
    }

    public void paste(ChartSelection selection) {
        throw new UnsupportedOperationException();
    }

    public void addSelectionListener(ChartSelectionListener listener) {
        myListeners.add(listener);
    }

    public void removeSelectionListener(ChartSelectionListener listener) {
        myListeners.remove(listener);
    }
}
