
package model.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HitPointAPI {

    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("window_id")
    @Expose
    private String windowId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("view_name")
    @Expose
    private String viewName;
    @SerializedName("heatmap_base64")
    @Expose
    private String heatmapBase64;
    @SerializedName("base_rate")
    @Expose
    private String baseRate;
    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("uudid")
    @Expose
    private String uudid;
    @SerializedName("views_blocks")
    @Expose
    private List<ViewsBlock> viewsBlocks = null;

    public HitPointAPI(String deviceId, String windowId, String message, String version, String viewName, String heatmapBase64, String baseRate, String apiKey, String uudid, List<ViewsBlock> viewsBlocks) {
        this.deviceId = deviceId;
        this.windowId = windowId;
        this.message = message;
        this.version = version;
        this.viewName = viewName;
        this.heatmapBase64 = heatmapBase64;
        this.baseRate = baseRate;
        this.apiKey = apiKey;
        this.uudid = uudid;
        this.viewsBlocks = viewsBlocks;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getWindowId() {
        return windowId;
    }

    public void setWindowId(String windowId) {
        this.windowId = windowId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getHeatmapBase64() {
        return heatmapBase64;
    }

    public void setHeatmapBase64(String heatmapBase64) {
        this.heatmapBase64 = heatmapBase64;
    }

    public String getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(String baseRate) {
        this.baseRate = baseRate;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUudid() {
        return uudid;
    }

    public void setUudid(String uudid) {
        this.uudid = uudid;
    }

    public List<ViewsBlock> getViewsBlocks() {
        return viewsBlocks;
    }

    public void setViewsBlocks(List<ViewsBlock> viewsBlocks) {
        this.viewsBlocks = viewsBlocks;
    }

}
