# -*- coding: cp1252 -*-
import wx
from run import CompetenceServer
from multiprocessing import Pool

TRAY_TOOLTIP = 'db-competence'
TRAY_ICON = ['img/icon_red.png', 'img/icon_yellow.png', 'img/icon_green.png']

def create_menu_item(menu, label, func):
    item = wx.MenuItem(menu, -1, label)
    menu.Bind(wx.EVT_MENU, func, id=item.GetId())
    menu.AppendItem(item)
    return item

class TaskBarIcon(wx.TaskBarIcon):
    def __init__(self):
        super(TaskBarIcon, self).__init__()
        self.set_icon(TRAY_ICON[0])
        self.Bind(wx.EVT_TASKBAR_LEFT_DOWN, self.on_left_down)
	self.server = CompetenceServer()

    def CreatePopupMenu(self):
        menu = wx.Menu()
        create_menu_item(menu, 'Run Server', self.on_run)
        create_menu_item(menu, 'Stop Server', self.on_stop)
        menu.AppendSeparator()
        create_menu_item(menu, 'Exit', self.on_exit)
        return menu

    def set_icon(self, path):
        icon = wx.IconFromBitmap(wx.Bitmap(path))
        self.SetIcon(icon, TRAY_TOOLTIP)

    def change_icon(self, numb):
	self.RemoveIcon()
	self.set_icon(TRAY_ICON[numb])

    def on_left_down(self, event):
        print 'Tray icon was left-clicked.'

    def on_run(self, event):
        self.change_icon(1)
	self.server.runServer()
	self.change_icon(2)
	print "on run ready"

    def on_stop(self, event):
	self.server.stopServer()
        self.change_icon(0)
	

    def on_exit(self, event):
        wx.CallAfter(self.Destroy)

    def callback():
	print "callback"
	self.seticon(0)


def main():
	app = wx.App()
        TaskBarIcon()
	app.MainLoop()


if __name__ == '__main__':
    	main()
