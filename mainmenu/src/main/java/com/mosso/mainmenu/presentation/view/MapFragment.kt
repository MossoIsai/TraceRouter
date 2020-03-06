package com.mosso.mainmenu.presentation.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.mosso.mainmenu.R
import com.mosso.mainmenu.domain.models.Points
import com.mosso.mainmenu.domain.models.RouteDomain
import com.mosso.mainmenu.presentation.viewmodels.MapViewModel
import com.mosso.shared.view.ScreenState
import kotlinx.android.synthetic.main.layuout_cardview.*
import org.koin.android.viewmodel.ext.android.viewModel


class MapFragment : Fragment(), OnMapReadyCallback {
    private val viewModel: MapViewModel by viewModel()
    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
        /*traceRouteBtn.setOnClickListener {
            viewModel.getGoogleRoute(
                18.6438631, -99.1609114,
                18.9320308, -99.3106054,
                "driving",
                "less_diving",
                ""

            )

        }*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_trace_route, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when (item.itemId) {
            R.id.reload -> {
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        })
    }


    private fun updateUI(screenState: ScreenState<MapRouteState>?) {
        when (screenState) {
            ScreenState.Loading -> showProgress()
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(renderState: MapRouteState) {
        hideProgress()
        when (renderState) {
            is MapRouteState.DisplayMapRoute -> setItems(renderState.routeDomain)
            is MapRouteState.ShowMessage -> showMessage(renderState.message)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        viewModel.mapRouteState.observe(::getLifecycle, ::updateUI)
        this.googleMap = googleMap
        this.googleMap ?: return
        with(this.googleMap) {
            this?.setMapStyle(MapStyleOptions.loadRawResourceStyle(activity, R.raw.stylemapblack))
        }
    }

    private fun showProgress() {

    }

    private fun hideProgress() {

    }

    private fun setItems(routeDomain: RouteDomain) {
        with(this.googleMap) {
            this?.addPolyline(PolylineOptions().apply {
                addAll(getLocationsList(routeDomain.points))
                width(STROKE_WIDTH_PX.toFloat())
                color(R.color.colorBlack)
                geodesic(true)
                addMarker(
                    MarkerOptions().position(routeDomain.originLocation).title(routeDomain.originAddress)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.circle_point))
                )

                addMarker(
                    MarkerOptions().position(routeDomain.destinationLocation).title(routeDomain.destinationAddress)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.squared_point))
                )
            })


            this?.moveCamera(
                CameraUpdateFactory.newLatLngZoom(routeDomain.originLocation, 15f)
            )
        }
        locationDestinationLabel.text = routeDomain.destinationAddress
        locationKilometerLabel.text = routeDomain.distance.text
    }

    private fun showMessage(message: String) {

    }

    private fun getLocationsList(points: MutableList<Points>): MutableList<LatLng> {
        val locations: MutableList<LatLng> = arrayListOf()
        for (item in points) {
            locations.add(item.starPoint)
            locations.add(item.endPoint)
        }
        return locations
    }

    companion object {
        const val STROKE_WIDTH_PX = 10
    }


}