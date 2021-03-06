package com.github.masahirosuzuka.phonegapguidesigner.component;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.xml.XmlFile;
import com.intellij.spring.model.utils.SpringModelUtils;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by masahiro on 2014/08/24.
 */
public class JQueryMobile {
  public static final String name = "jQueryMobile";
  public static final String description = "";
  public static final String version = "1.4";
  public static final String[] metaInfo = new String[]{
      "<link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.css\" />",
      "<script src=\"http://code.jquery.com/jquery-1.11.1.min.js\"></script>",
      "<script src=\"http://code.jquery.com/jquery-1.11.1.min.js\"></script>",
  };
  public static Map<String, String> map =
      new HashMap<String, String>(){{
        put(Button.name, Button.body);
        put(CheckBoxRadio.name, CheckBoxRadio.body);
        put(Collapsible.name, Collapsible.body);
        put(CollapsibleSet.name, CollapsibleSet.body);
        put(ControlGroupe.name, ControlGroupe.body);
        put(Flipswitch.name, Flipswitch.body);
        put(ListView.name, ListView.body);
        put(Navbar.name, Navbar.body);
        put(Page.name, Page.body);
        put(Panel.name, Panel.body);
        put(RangeSlider.name, RangeSlider.body);
        put(Selectmenu.name, Selectmenu.body);
        put(Slider.name, Slider.body);
        put(Table.name, Table.body);
        put(Tabs.name, Tabs.body);
        put(TextInput.name, TextInput.body);
        put(Toolbar.name, Toolbar.body);
      }};

  public JQueryMobile(Project project) {
    URL url = this.getClass().getResource("jQueryMobile.xml");
    VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByPath(url.getPath());
    XmlFile xmlFile = (XmlFile)PsiManager.getInstance(project).findFile(virtualFile);
  }
/*
  public class Button implements Widget {
    public String name;

    public void setName(String name) {
      this.name = name;
    }
  }
  */

  static class Button implements Widget{
    public static final String name = "Button";
    public static final String body = "<input type=\"button\" value=\"Button\">";
  }

  public class CheckBoxRadio implements Widget {
    public static final String name = "CheckBoxRadio";
    public static final String body = "<label><input type=\"checkbox\" name=\"checkbox-0\">CheckBoxRadio</label>";
  }

  public class Collapsible implements Widget {
    public static final String name = "Collapsible";
    public static final String body = "<div data-role=\"collapsible\">\n" +
        "<h3>Header</h3>\n" +
        "<p>body</p>\n" +
        "</div>";
  }


  public class CollapsibleSet implements Widget {
    public static final String name = "CollapsibleSet";
    public static final String body = "<div data-role=\"collapsible-set\">\n" +
              "<div data-role=\"collapsible\" data-collapsed=\"false\">\n" +
                "<h3>Section 1</h3>\n" +
                "<p>I'm the collapsibleset content for section 1. My content is initially visible.</p>\n" +
              "</div>\n" +
              "<div data-role=\"collapsible\">\n" +
                "<h3>Section 2</h3>\n" +
                "<p>I'm the collapsibleset content for section 2.</p>\n" +
              "</div>\n" +
            "</div>";
  }

  public class ControlGroupe implements Widget {
    public static final String name = "ControlGroupe";
    public static final String body =  "<div data-role=\"controlgroup\">\n" +
              "<a href=\"#\" class=\"ui-btn ui-corner-all\">Yes</a>\n" +
              "<a href=\"#\" class=\"ui-btn ui-corner-all\">No</a>\n" +
            "</div>";
  }

  /*
  public class Dialog extends Widget {
    @Override
    public String getName() {
      return "Dialog";
    }

    @Override
    public String getBody() {
      return "";
    }

  }
  */

  /*
  public class FilterableContent extends Widget {
    @Override
    public String getName() {
      return "FilterableContent";
    }

    @Override
    public String getBody() {
      return "FilterableContent";
    }
  }
  */

  public class Flipswitch implements Widget {
    public static final String name = "Flipswitch";
    public static final String body = "<fieldset>\n" +
              "<div data-role=\"fieldcontain\">\n" +
                "<label for=\"checkbox-based-flipswitch\">Checkbox-based:</label>\n" +
                "<input type=\"checkbox\" id=\"checkbox-based-flipswitch\" data-role=\"flipswitch\">\n" +
              "</div>\n" +
            "</fieldset>";
  }

  public class ListView implements Widget {
    public static final String name =  "ListView";
    public static final String body = "<ul data-role=\"listview\">\n" +
                "<li><a href=\"#\">list1</a></li>\n" +
                "<li><a href=\"#\">list2</a></li>\n" +
             "</ul>";
  }

  /*
  public class Loader extends Widget {

    @Override
    public String getName() {
      return "Loader";
    }

    @Override
    public String getBody() {
      return "";
    }
  }
  */

  public class Navbar implements Widget {
    public static final String name = "Navbar";
    public static final String body = "<div data-role=\"navbar\">\n" +
                "<ul>\n" +
                  "<li><a href=\"a.html\">One</a></li>\n" +
                  "<li><a href=\"b.html\">Two</a></li>\n" +
                "</ul>\n" +
             "</div>";
  }

  public class Page implements Widget {
    public static final String name = "Page";
    public static final String body = "<div data-role=\"page\" id=\"page1\"></div>";
  }

  /*
  public class PageContainer extends Widget {

    @Override
    public String getName() {
      return "PageContainer";
    }

    @Override
    public String getBody() {
      return "";
    }
  }
  */

  public class Panel implements Widget {
    public static final String name = "Panel";
    public static final String body = "<div data-role=\"panel\" id=\"mypanel\" data-position=\"right\" data-display=\"push\">\n</div>";
  }

  /*
  public class Popup extends Widget {

    @Override
    public String getName() {
      return "Popup";
    }

    @Override
    public String getBody() {
      return "";
    }
  }
  */

  public class RangeSlider implements Widget {
    public static final String name = "RangeSlider";
    public static final String body = "<div data-role=\"rangeslider\">\n" +
                "<input name=\"range-1a\" id=\"range-1a\" min=\"0\" max=\"100\" value=\"0\" type=\"range\" />\n" +
                "<input name=\"range-1b\" id=\"range-1b\" min=\"0\" max=\"100\" value=\"0\" type=\"range\" />\n" +
             "</div>";

  }

  public class Selectmenu implements Widget {
    public static final String name = "Selectmenu";
    public static final String body = "<div class=\"ui-field-contain\">\n" +
                "<label for=\"select-choice-1\" class=\"select\">Shipping method:</label>\n" +
                "<select name=\"select-choice-1\" id=\"select-choice-1\">\n" +
                  "<option value=\"standard\">Standard: 7 day</option>\n" +
                  "<option value=\"rush\">Rush: 3 days</option>\n" +
                  "<option value=\"express\">Express: next day</option>\n" +
                  "<option value=\"overnight\">Overnight</option>\n" +
                "</select>\n" +
              "</div>";
  }

  public class Slider implements Widget {
    public static final String name = "Slider";
    public static final String body = "<input type=\"range\" name=\"slider-1\" id=\"slider-1\" value=\"60\" min=\"0\" max=\"100\">";

  }

  public class Table implements Widget {
    public static final String name = "Table";
    public static final String body = "<table data-role=\"table\" id=\"my-table\" data-mode=\"reflow\"></table>";
  }

  /*
  public class ColumnToggleTable extends Widget {

    @Override
    public String getName() {
      return "ColumnToggleTable";
    }

    @Override
    public String getBody() {
      return "";
    }

  }
  */

  /*
  public class ReflowTable extends Widget {
    @Override
    public String getName() {
      return "ReflowTable";
    }

    @Override
    public String getBody() {

    }
  }
  */

  public class Tabs implements Widget {
    public static final String name = "Tab";
    public static final String body = "<div data-role=\"tabs\"></div>";
  }

  public class TextInput implements Widget {
    public static final String name = "TextInput";
    public static final String body = "<input type=\"text\" name=\"name\" id=\"basic\" value=\"\">";

  }

  public class Toolbar implements Widget {
    public static final String name = "Toolbar";
    public static final String body = "<div data-role=\"header\">\n" +
                "<a href=\"index.html\" data-icon=\"delete\">Cancel</a>\n" +
                "<h1>Edit Contact</h1>\n" +
                "<a href=\"index.html\" data-icon=\"check\">Save</a>\n" +
             "</div>";
  }
}
