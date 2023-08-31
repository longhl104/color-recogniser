import { Component } from "@angular/core";
import { Meta, Title } from "@angular/platform-browser";
import { TabViewChangeEvent } from "primeng/tabview";
import { environment } from "src/environments/environment";

@Component({
  selector: "app-dev-tools",
  templateUrl: "./dev-tools.component.html",
  styleUrls: ["./dev-tools.component.scss"],
})
export class DevToolsComponent {
  tabs = [
    {
      name: "TFN Generator",
    },
    {
      name: "File Diff",
    },
    {
      name: "JSON Escape / Unescape / Beautify",
    },
  ];

  activeIndex = environment.production ? 0 : 2;

  constructor(private $title: Title, $meta: Meta) {
    $title.setTitle(this.tabs[0].name);

    $meta.updateTag(
      {
        name: "og:description",
        content: "All-in-one tools for developers.",
      },
      "property='og:description'"
    );
  }

  onTabChange(event: TabViewChangeEvent) {
    this.$title.setTitle(this.tabs[event.index].name);
  }
}