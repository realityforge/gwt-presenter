package net.customware.gwt.presenter.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.binder.GinLinkedBindingBuilder;
import com.google.inject.Singleton;
import net.customware.gwt.presenter.client.Display;
import net.customware.gwt.presenter.client.Presenter;
import static com.google.inject.name.Names.named;

public abstract class AbstractPresenterModule
  extends AbstractGinModule
{
  /**
   * Convenience method for binding a presenter as well as it's display.
   *
   * @param <D>         The display type.
   * @param presenter   The presenter.
   * @param display     The display type.
   * @param displayImpl The display implementation.
   */
  protected final <D extends Display> void bindPresenter( final Class<? extends Presenter> presenter,
                                                          final Class<D> display,
                                                          final Class<? extends D> displayImpl )
  {
    bind( presenter ).in( Singleton.class );
    bindDisplay( display, displayImpl );
  }

  /**
   * Convenience method for binding a display implementation.
   *
   * @param <D>         The display interface type
   * @param display     The display interface
   * @param displayImpl The display implementation
   */
  protected final <D extends Display> void bindDisplay( final Class<D> display, final Class<? extends D> displayImpl )
  {
    bind( display ).to( displayImpl );
  }

  /**
   * Convenience method for binding a type to a {@link com.google.inject.name.Named} attribute. Use
   * it something like this:
   * <p/>
   * <pre>
   * bindNamed( MyType.class, &quot;Foo&quot; ).to( MyImplementation.class );
   * </pre>
   *
   * @param <T>   The type.
   * @param type  The type.
   * @param named The string to name with.
   * @return the binding builder.
   */
  protected final <T> GinLinkedBindingBuilder<T> bindNamed( final Class<T> type, final String named )
  {
    return bind( type ).annotatedWith( named( named ) );
  }
}
