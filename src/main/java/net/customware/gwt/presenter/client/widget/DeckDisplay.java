package net.customware.gwt.presenter.client.widget;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Widget;

public class DeckDisplay
  extends DeckPanel
  implements WidgetContainerDisplay
{
  public void addWidget( final Widget widget )
  {
    add( widget );
  }

  public void removeWidget( final Widget widget )
  {
    remove( widget );
  }

  public void showWidget( final Widget widget )
  {
    final int index = this.getWidgetIndex( widget );
    if ( index >= 0 )
    {
      showWidget( index );
    }
  }

  public Widget asWidget()
  {
    return this;
  }
}
