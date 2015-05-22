package uzuzjmd.competence.gui.client.context;

public class NativeContext {
	public static native void showPreview(String url, String selector,
			String whereTo)/*-{
		$wnd.preview(url, selector, whereTo);
		//		$wnd.previewdebug(url, selector, whereTo);
	}-*/;

	public static void showMoodlePreview(String url, String whereTo) {
		showPreview(url, ".region-content", whereTo);
		showPreview(url, ".region-main", whereTo);
	}
}
