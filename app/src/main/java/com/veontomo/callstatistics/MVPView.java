package com.veontomo.callstatistics;

/**
 * View interface of MVP architecture
 */
interface MVPView {
    void loadData(DiagramData data);

    void onError(String text);
}
