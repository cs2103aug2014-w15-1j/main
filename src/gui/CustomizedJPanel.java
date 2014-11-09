package gui;

/**
 * a interface to implement substitutable
 * <code>JPanel</code> component to customize <strong>GUI</strong>.
 * 
 * <p>
 * Note that many different types of information may need to be passed to the component.
 * Sometimes only a subset of them need to be passed.It will be not a good idea to pass all arguments via constructor due to limitation of Java.
 * 
 * <p>
 * Therefore, inside every class implements CustomizedJPanel, a <em>construct()</em>
 * method is predefined. All the construction regarding the inner component of a CustomizedJPanel will completed inside this method.
 * Then all the initialization should be finished before <em>construct()</em> is invoked.
 * This mechanism guarantees that all necessary information will be passed from <code>GuiInfoTranslator</code> to <code>BasicGui</code>.
 * 
 * @author A0119391A
 */

public interface CustomizedJPanel {

	public void construct();

}
