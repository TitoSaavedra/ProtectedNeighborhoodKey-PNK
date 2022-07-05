package cl.inacap.pnk.ui;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.sdsmdg.harjot.vectormaster.VectorMasterView;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import cl.inacap.pnk.R;
import cl.inacap.pnk.ui.fragments.home.HomeFragment;
import cl.inacap.pnk.ui.util.CurvedBottomNavigation;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Clase del layout Activity_inicio
 * Sirve para realizar la programacion de la funcionalidad de la vista
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class Inicio extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    //Campos de la clase
    private AppBarConfiguration mAppBarConfiguration;
    CurvedBottomNavigation bottomNavigation;
    VectorMasterView item_historial,item_inicio,item_visitas;
    RelativeLayout lin_id;
    PathModel outline;
    private ImageView imgPerfil;

    /**
     * Metodo de la creacion de la clase con la vista
     * @param savedInstanceState instacia de la aplicación
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Asignacion de valores de la vista
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_visitas, R.id.nav_historial)
                .setDrawerLayout(drawer)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //View
        bottomNavigation =(CurvedBottomNavigation) findViewById(R.id.bottom_nav);
        item_visitas = (VectorMasterView)findViewById(R.id.item_visitas);
        item_inicio = (VectorMasterView)findViewById(R.id.item_inicio);
        item_historial = (VectorMasterView)findViewById(R.id.item_historial);
        lin_id=(RelativeLayout) findViewById(R.id.lin_id);

        bottomNavigation.inflateMenu(R.menu.main_menu);
        //Animación de la barra de navegacion segun el boton
        bottomNavigation.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                draw(6);
                lin_id.setX(bottomNavigation.mFirstCurveControlPoint1.x);
                item_historial.setVisibility(View.GONE);
                item_inicio.setVisibility(View.GONE);
                item_visitas.setVisibility(View.VISIBLE);
                drawAnimation(item_visitas);
                return false;
            }
        });
        bottomNavigation.getMenu().getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                draw(2);
                lin_id.setX(bottomNavigation.mFirstCurveControlPoint1.x);
                item_historial.setVisibility(View.GONE);
                item_inicio.setVisibility(View.VISIBLE);
                item_visitas.setVisibility(View.GONE);
                drawAnimation(item_inicio);
                return false;
            }
        });
        bottomNavigation.getMenu().getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                draw();
                lin_id.setX(bottomNavigation.mFirstCurveControlPoint1.x);
                item_historial.setVisibility(View.VISIBLE);
                item_inicio.setVisibility(View.GONE);
                item_visitas.setVisibility(View.GONE);
                drawAnimation(item_historial);
                return false;
            }
        });

        //Event bottom nav
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        bottomNavigation.setSelectedItemId(R.id.item_inicio);
        NavigationUI.setupWithNavController(bottomNavigation, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    /**
     * Metodo para realizar animacion cuando es seleccionado un item de la barra de navegación
     * @param menuItem Item seleccionado
     * @return Verdadero para confirmar la seleccion
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_historial:
                //Animation
                draw(6);
                lin_id.setX(bottomNavigation.mFirstCurveControlPoint1.x);
                item_historial.setVisibility(View.VISIBLE);
                item_inicio.setVisibility(View.GONE);
                item_visitas.setVisibility(View.GONE);
                drawAnimation(item_historial);
                break;
            case R.id.nav_home:
                //Animation
                draw(2);
                lin_id.setX(bottomNavigation.mFirstCurveControlPoint1.x);
                item_historial.setVisibility(View.GONE);
                item_inicio.setVisibility(View.VISIBLE);
                item_visitas.setVisibility(View.GONE);
                drawAnimation(item_inicio);
                break;
            case R.id.nav_visitas:
                //Animation
                draw();
                lin_id.setX(bottomNavigation.mFirstCurveControlPoint1.x);
                item_historial.setVisibility(View.GONE);
                item_inicio.setVisibility(View.GONE);
                item_visitas.setVisibility(View.VISIBLE);
                drawAnimation(item_visitas);
                break;
        }
        return true;
    }

    /**
     * Metodo para realizar la animación de desplazamiento de la barra de navegación
     */
    private void draw() {
        bottomNavigation.mFirstCurveStartPoint.set((bottomNavigation.mNavigationBarWidth*10/12)
                -(bottomNavigation.CURVE_CIRCLE_RADIUS*2)
                -(bottomNavigation.CURVE_CIRCLE_RADIUS/3),0);

        bottomNavigation.mFirstCurveEndPoint.set(bottomNavigation.mNavigationBarWidth*10/12,
                bottomNavigation.CURVE_CIRCLE_RADIUS
                        +(bottomNavigation.CURVE_CIRCLE_RADIUS/4));

        bottomNavigation.mSecondCurveStartPoint = bottomNavigation.mFirstCurveEndPoint;
        bottomNavigation.mSecondCurveEndPoint.set((bottomNavigation.mNavigationBarWidth*10/12)
                +(bottomNavigation.CURVE_CIRCLE_RADIUS*2)+(bottomNavigation.CURVE_CIRCLE_RADIUS/3),0);

        bottomNavigation.mFirstCurveControlPoint1.set(bottomNavigation.mFirstCurveStartPoint.x
                        +bottomNavigation.CURVE_CIRCLE_RADIUS +(bottomNavigation.CURVE_CIRCLE_RADIUS/4),
                bottomNavigation.mFirstCurveStartPoint.y);

        bottomNavigation.mFirstCurveControlPoint2.set(bottomNavigation.mFirstCurveEndPoint.x -
                        (bottomNavigation.CURVE_CIRCLE_RADIUS*2)+bottomNavigation.CURVE_CIRCLE_RADIUS,
                bottomNavigation.mFirstCurveEndPoint.y);

        // Same with second
        bottomNavigation.mSecondCurveControlPoint1.set(bottomNavigation.mSecondCurveStartPoint.x
                        + (bottomNavigation.CURVE_CIRCLE_RADIUS*2) - bottomNavigation.CURVE_CIRCLE_RADIUS,
                bottomNavigation.mSecondCurveStartPoint.y);
        bottomNavigation.mSecondCurveControlPoint2.set(bottomNavigation.mSecondCurveEndPoint.x -
                (bottomNavigation.CURVE_CIRCLE_RADIUS + (bottomNavigation.CURVE_CIRCLE_RADIUS/4)),bottomNavigation.mSecondCurveEndPoint.y);
    }

    /**
     * Metodo para cambiar el diseño de los iconos de la barra de navegación
     * @param fab icono a cambiar
     */
    private void drawAnimation(final VectorMasterView fab) {
        outline = fab.getPathModelByName("outline");
        outline.setStrokeColor(Color.WHITE);
        outline.setTrimPathEnd(0.0f);
        //Init valueAnimator
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0.0f,1.0f);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                outline.setTrimPathEnd((Float)valueAnimator.getAnimatedValue());
                fab.update();
            }
        });
        valueAnimator.start();
    }

    /**
     * Metodo para animacion de seleccion de icono
     * @param i posicion del icono
     */
    private void draw(int i) {
        bottomNavigation.mFirstCurveStartPoint.set((bottomNavigation.mNavigationBarWidth/i)
                -(bottomNavigation.CURVE_CIRCLE_RADIUS*2)-(bottomNavigation.CURVE_CIRCLE_RADIUS/3),0);

        bottomNavigation.mFirstCurveEndPoint.set(bottomNavigation.mNavigationBarWidth/i,bottomNavigation.CURVE_CIRCLE_RADIUS
                +(bottomNavigation.CURVE_CIRCLE_RADIUS/4));

        bottomNavigation.mSecondCurveStartPoint = bottomNavigation.mFirstCurveEndPoint;
        bottomNavigation.mSecondCurveEndPoint.set((bottomNavigation.mNavigationBarWidth/i)
                +(bottomNavigation.CURVE_CIRCLE_RADIUS*2)+(bottomNavigation.CURVE_CIRCLE_RADIUS/3),0);

        bottomNavigation.mFirstCurveControlPoint1.set(bottomNavigation.mFirstCurveStartPoint.x
                        +bottomNavigation.CURVE_CIRCLE_RADIUS +(bottomNavigation.CURVE_CIRCLE_RADIUS/4),
                bottomNavigation.mFirstCurveStartPoint.y);

        bottomNavigation.mFirstCurveControlPoint2.set(bottomNavigation.mFirstCurveEndPoint.x -
                        (bottomNavigation.CURVE_CIRCLE_RADIUS*2)+bottomNavigation.CURVE_CIRCLE_RADIUS,
                bottomNavigation.mFirstCurveEndPoint.y);

        // Same with second
        bottomNavigation.mSecondCurveControlPoint1.set(bottomNavigation.mSecondCurveStartPoint.x
                        + (bottomNavigation.CURVE_CIRCLE_RADIUS*2) - bottomNavigation.CURVE_CIRCLE_RADIUS,
                bottomNavigation.mSecondCurveStartPoint.y);
        bottomNavigation.mSecondCurveControlPoint2.set(bottomNavigation.mSecondCurveEndPoint.x -
                (bottomNavigation.CURVE_CIRCLE_RADIUS + (bottomNavigation.CURVE_CIRCLE_RADIUS/4)),bottomNavigation.mSecondCurveEndPoint.y);
    }



}
