package ru.hitpoint.lib.hitpoint.views;

import android.view.View;

public class FloatingViewConfigs {
    FloatingView.Gravity buttonGravity = FloatingView.Gravity.RIGHT;
    boolean useGravity = true;
    View buttonView;

    private FloatingViewConfigs() {
    }

    public static Builder newBuilder(){
        return new FloatingViewConfigs().new Builder();
    }

    public class Builder{
        private Builder() {
        }

        public Builder setButtonGravity(FloatingView.Gravity buttonGravity){
            FloatingViewConfigs.this.buttonGravity = buttonGravity;
            return this;
        }

        public Builder setUseGravity(boolean useGravity){
            FloatingViewConfigs.this.useGravity = useGravity;
            return this;
        }

        public Builder setButtonView(View view){
            FloatingViewConfigs.this.buttonView = view;
            return this;
        }

        public FloatingViewConfigs build(){
            return FloatingViewConfigs.this;
        }
    }
}
