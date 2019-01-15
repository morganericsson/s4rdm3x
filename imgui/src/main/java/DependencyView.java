import glm_.vec2.Vec2;
import gui.ImGuiWrapper;
import se.lnu.siq.s4rdm3x.model.CNode;

import static imgui.ImguiKt.COL32;

public class DependencyView {

    void doDependencyView(ImGuiWrapper a_imgui, CNode a_center) {
        final int color = COL32(175, 175, 175, 255);

        Vec2 size = a_imgui.imgui().getWindowSize();
        Vec2 offset = a_imgui.imgui().getWindowPos();

        String name = a_center.getName();
        Vec2 stringSize = a_imgui.calcTextSize(name, false);

        Vec2 pos = offset.plus(size.minus(stringSize).div(2));

        a_imgui.addText(pos, color, name);

    }
}
