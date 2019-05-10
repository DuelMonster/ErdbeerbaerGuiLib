package de.erdbeerbaerlp.guilib.components;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
//GuiButtonExt
public class Button extends GuiComponent{
	
	protected ResourceLocation BUTTON_ICON;
	private Runnable callback;
	protected String displayString;
    
	public static class DefaultButtonIcons {
		public static final ResourceLocation ADD = new ResourceLocation("eguilib:textures/gui/buttonicons/add.png");
		public static final ResourceLocation DELETE = new ResourceLocation("eguilib:textures/gui/buttonicons/delete.png");
		public static final ResourceLocation PLAY = new ResourceLocation("eguilib:textures/gui/buttonicons/play.png");
		public static final ResourceLocation PAUSE = new ResourceLocation("eguilib:textures/gui/buttonicons/pause.png");
		public static final ResourceLocation STOP = new ResourceLocation("eguilib:textures/gui/buttonicons/stop.png");
		public static final ResourceLocation SAVE = new ResourceLocation("eguilib:textures/gui/buttonicons/save.png");
		public static final ResourceLocation NEW = new ResourceLocation("eguilib:textures/gui/buttonicons/new.png");
		public static final ResourceLocation FILE = new ResourceLocation("eguilib:textures/gui/buttonicons/file.png");
		public static final ResourceLocation FILE_TXT = new ResourceLocation("eguilib:textures/gui/buttonicons/file_txt.png");
		public static final ResourceLocation FILE_NBT = new ResourceLocation("eguilib:textures/gui/buttonicons/file_nbt.png");
		public static final ResourceLocation FILE_BIN = new ResourceLocation("eguilib:textures/gui/buttonicons/file_bin.png");
		public static final ResourceLocation ARROW_RIGHT = new ResourceLocation("eguilib:textures/gui/buttonicons/arrow-right.png");
		public static final ResourceLocation ARROW_LEFT = new ResourceLocation("eguilib:textures/gui/buttonicons/arrow-left.png");
		
	}
	public Button(int xPos, int yPos, String displayString) {
		this(xPos, yPos, 100, displayString);
	}
	
	public Button(int xPos, int yPos, int width, String displayString) {
		this(xPos, yPos, width, 20, displayString);
	}
	public Button(int xPos, int yPos, int width, String displayString, ResourceLocation icon) {
		this(xPos, yPos, width, 20, displayString, icon);
	}
	public Button(int xPos, int yPos, int width, int height, String displayString) {
		this(xPos, yPos, width, height, displayString, null);
	}
	public Button(int xPos, int yPos, int width, int height, String displayString, ResourceLocation icon) {
		super(xPos, yPos, width, height);
		this.BUTTON_ICON = icon;
		this.displayString = displayString;
		
	}
	
	public Button(int xPos, int yPos, String string, ResourceLocation icon) {
		this(xPos, yPos, 100, string, icon);
	}

	public Button(int xPos, int yPos, ResourceLocation icon) {
		this(xPos, yPos, 20, "", icon);
	}

	public final void setClickListener(Runnable r) {
		this.callback = r;
	}
	public void onClick() {
		if(this.callback != null) this.callback.run();
	}
	/**
     * Draws this button to the screen.
     */
	@Override
	public void draw(int mouseX, int mouseY, float partial) {
        if (this.visible)
        {
            this.hovered = mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height;
            int k = this.getHoverState(this.hovered);
            GuiUtils.drawContinuousTexturedBox(BUTTON_TEXTURES, this.getX(), this.getY(), 0, 46 + k * 20, this.width, this.height, 200, 20, 2, 3, 2, 2, this.zLevel);
//            this.mouseDragged(mc, mouseX, mouseY);
            int color = 14737632;

			if (packedFGColour != 0)
            {
                color = packedFGColour;
            }
            else if (!this.enabled)
            {
                color = 10526880;
            }
            else if (this.hovered)
            {
                color = 16777120;
            }
            int bx = this.getX();
            int mwidth = this.width;
            if(BUTTON_ICON != null) {
            	Minecraft.getMinecraft().getTextureManager().bindTexture(BUTTON_ICON);
				drawModalRectWithCustomSizedTexture(bx + 2, getY() + 2, 0, 0, 16, 16, 16, 16);

				// ! MODIFY X !
				bx += 2 + 16;
				mwidth -= 16;
			}
            String buttonText = this.displayString;
            int strWidth = mc.fontRenderer.getStringWidth(buttonText);
            int ellipsisWidth = mc.fontRenderer.getStringWidth("...");
            if (strWidth > mwidth - 6 && strWidth > ellipsisWidth)
                buttonText = mc.fontRenderer.trimStringToWidth(buttonText, mwidth - 6 - ellipsisWidth).trim() + "...";

            this.drawCenteredString(mc.fontRenderer, buttonText, bx + mwidth / 2, this.getY() + (this.height - 8) / 2, color);
        }
        
    }
    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean mouseOver)
    {
        int i = 1;

        if (!this.enabled)
        {
            i = 0;
        }
        else if (mouseOver)
        {
            i = 2;
        }

        return i;
    }
	
	public final void setText(String text) {
		this.displayString = text;
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButton) throws IOException {
		if(mousePressed(mc, mouseX, mouseY)) {
			playPressSound();
			onClick();
		}
	}

    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        return this.visible && mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height;
    }
	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {
		
		
	}



	@Override
	public void keyTyped(char typedChar, int keyCode) throws IOException {
		
		
	}


	@Override
	public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		
		
	}
	
}
