package org.ikasan.dashboard.ui.search.listener;

import com.vaadin.flow.component.checkbox.Checkbox;
import org.ikasan.dashboard.ui.search.component.SolrSearchFilteringGrid;
import org.ikasan.solr.model.IkasanSolrDocument;

import java.util.HashMap;
import java.util.stream.Collectors;

public abstract class IkasanEventActionListener
{
    protected SolrSearchFilteringGrid searchResultsGrid;
    protected HashMap<String, Checkbox> selectionBoxes = new HashMap<>();
    protected HashMap<String, IkasanSolrDocument> selectionItems = new HashMap<>();
    protected Boolean selected = Boolean.FALSE;

    /**
     * Helper method to confirm that some events have been selected to be actioned.
     *
     * @return
     */
    protected boolean confirmSelectedEvents()
    {
        int numberSelectedBoxes = this.selectionBoxes.values().stream().filter(checkbox -> checkbox.getValue()).collect(Collectors.toList()).size();

        if(selected && numberSelectedBoxes > 0)
        {
            return true;
        }

        if(!selected && numberSelectedBoxes > 0)
        {
            return true;
        }

        return false;
    }

    /**
     * Helper method to confirm that some events should be actioned.
     *
     * @param document
     * @return
     */
    protected boolean shouldActionEvent(IkasanSolrDocument document)
    {
        if(this.selected)
        {
            if((this.selectionBoxes.containsKey(document.getId()) && this.selectionBoxes.get(document.getId()).getValue())
                || !this.selectionBoxes.containsKey(document.getId()))
            {
                return true;
            }
        }
        else
        {
            if((this.selectionBoxes.containsKey(document.getId()) && this.selectionBoxes.get(document.getId()).getValue()))
            {
                return true;
            }
        }

        return false;
    }

    public void setSelected(Boolean selected)
    {
        this.selected = selected;
    }
}
