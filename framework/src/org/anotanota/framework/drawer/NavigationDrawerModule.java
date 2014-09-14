package org.anotanota.framework.drawer;

import java.util.List;

import org.anotanota.framework.R;
import org.anotanota.framework.Activity;
import org.anotanota.framework.ActivityModule;
import org.anotanota.framework.Navigation;
import org.anotanota.framework.UIViewController;
import org.anotanota.framework.drawer.NavigationDrawer.Titles;
import org.anotanota.framework.drawer.NavigationDrawer.ViewControler;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ListView;
import dagger.Module;
import dagger.Provides;

@Module(library = true, complete = false)
public class NavigationDrawerModule {
	@Provides
	@NavigationDrawer.Layout
	DrawerLayout drawerLayout(Activity activity) {
		return (DrawerLayout) activity
				.findViewById(org.anotanota.framework.R.id.drawer_layout);
	}

	@Provides
	@NavigationDrawer.View
	ListView drawerView(Activity activity) {
		return (ListView) activity
				.findViewById(org.anotanota.framework.R.id.left_drawer);
	}


	@Provides
	@NavigationDrawer.Toggle
	ActionBarDrawerToggle toggle(Activity activity,
			@NavigationDrawer.Layout DrawerLayout layout,
			final NavigationDrawerViewController navigationDrawer,
			final Navigation navigation) {
		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(activity,
				layout, R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when a drawer has settled in a completely open state. */
			@Override
			public void onDrawerOpened(View drawerView) {
				navigation.navigateTo(navigationDrawer);
			}
		};

		layout.setDrawerListener(drawerToggle);
		return drawerToggle;
	}
}
