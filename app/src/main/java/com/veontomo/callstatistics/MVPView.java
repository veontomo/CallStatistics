package com.veontomo.callstatistics;

/**
 * View interface of MVP architecture
 */
public interface MVPView {
    void loadData(DiagramData data);

    void onError(String text);
}
