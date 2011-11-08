/*
GanttProject is an opensource project management tool. License: GPL2
Copyright (C) 2005-2011 GanttProject Team

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
package net.sourceforge.ganttproject.action.view;

import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;

import net.sourceforge.ganttproject.GPView;
import net.sourceforge.ganttproject.GanttProject;
import net.sourceforge.ganttproject.action.GPAction;
import net.sourceforge.ganttproject.action.ViewToggleAction;
import net.sourceforge.ganttproject.chart.Chart;
import net.sourceforge.ganttproject.plugins.PluginManager;

/**
 * Collection of actions present in the view menu
 */
public class ViewMenu extends JMenu {
    public ViewMenu(final GanttProject project, String key) {
        super(GPAction.createVoidAction(key));

        ViewCycleAction viewCycleAction = new ViewCycleAction(project.getTabs());

        List<Chart> charts = PluginManager.getCharts();
        add(viewCycleAction);
        if (!charts.isEmpty()) {
            addSeparator();
        }
        for (Chart chart : charts) {
            chart.init(project.getProject());
            GPView view = project.getViewManager().createView(chart, null);
            add(new JCheckBoxMenuItem(new ViewToggleAction(chart, view)));
        }
    }
}
