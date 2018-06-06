
package model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewsBlock {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("view_id")
    @Expose
    private String viewId;
    @SerializedName("view_image_base64")
    @Expose
    private String viewImageBase64;
    @SerializedName("coordinates_by_x")
    @Expose
    private String coordinatesByX;
    @SerializedName("coordinates_by_y")
    @Expose
    private String coordinatesByY;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("rate")
    @Expose
    private String rate;

    public ViewsBlock(String type, String viewId, String viewImageBase64, String coordinatesByX, String coordinatesByY, String message, String rate) {
        this.type = type;
        this.viewId = viewId;
        this.viewImageBase64 = viewImageBase64;
        this.coordinatesByX = coordinatesByX;
        this.coordinatesByY = coordinatesByY;
        this.message = message;
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String getViewImageBase64() {
        return viewImageBase64;
    }

    public void setViewImageBase64(String viewImageBase64) {
        this.viewImageBase64 = viewImageBase64;
    }

    public String getCoordinatesByX() {
        return coordinatesByX;
    }

    public void setCoordinatesByX(String coordinatesByX) {
        this.coordinatesByX = coordinatesByX;
    }

    public String getCoordinatesByY() {
        return coordinatesByY;
    }

    public void setCoordinatesByY(String coordinatesByY) {
        this.coordinatesByY = coordinatesByY;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}
