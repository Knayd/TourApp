package com.example.applaudo.tourguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PlaceFragment extends Fragment implements PlacesAdapter.OnItemClicked {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<Place> placesList = new ArrayList<>();

        View v = inflater.inflate(R.layout.fragment_places, container, false);

        RecyclerView rv = v.findViewById(R.id.rv_fragments);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        //This is where I retrieve the tab number
        Bundle getTabData = getArguments();
        if (getTabData != null) {
            int mLoadType = getTabData.getInt("tabnumber");
            if (mLoadType == 0) {
                //TAB FOOD
                placesList.add(new Place(R.drawable.la_bodega_2, "La Bodega Italiana", "La Gran Vía, Carretera Panamericana y Calle Chiltiupán. Antiguo Cuscatlán, La Libertad, Centro Comercial La Gran Vía, Local 401 - 402", "bodegaitaliana.com.sv", "2536 8888", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.zanzibar_2, "Zanzibar", "Zanzibar, Colonia San Benito, San Salvador", "www.barzanzibar.com", "2511 4282", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.al_pomodoro_2, "Al Pomodoro", "Ave. La Revolución y Calle Circunvalación No. 184, San Salvador CP 1101", "alpomodoro.com.sv", "2243 7388", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.bravissimo_2, "Bravissimo", "Col. Escalón No. 127, Pje Istmania, San Salvador", "-", "2223 3986", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.la_pizzeria_2, "La Pizzeria", "Plaza Los Castaños, Avenida Masferrer Norte, San Salvador", "www.lapizzeria.com", "2566 6574", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.panino_2, "Panino's", "Playa el Tunco Plaza Tunco Town, El Tunco El Salvador", "www.facebook.com/pg/paninos.sv/about/?ref=page_internal", "7698 6999", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.royal_2, "Royal", "87 Avenida Sur, San Salvado", "-", "2264 0051", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.style68_2, "Style 68", "16 Avenida Nte, Santa Tecla", "www.facebook.com/Style68chinesecuisine", "2246 4875", getResources().getString(R.string.description_placeholder), "", ""));

            } else if (mLoadType == 1) {
                //TAB COFFEE
                //container.setBackgroundColor(Color.BLACK);
                placesList.add(new Place(R.drawable.juan_valdez_2, "Juan Valdez Café Galerías", "Juan Valdez Café Galerías, San Salvador", "www.juanvaldezcafe.com", "2264 9999", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.andian_2, "Andian Pâttiserie & Cafe", "Plaza Catalonia #260 El, Calle La Mascota, San Salvador", "www.facebook.com/AndianSV/?utm_source=tripadvisor&utm_medium=referral", "2223 8770", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.gecko_2, "Gecko Cafe", "89 Avenida Norte, San Salvador", "www.facebook.com/Paletas-Gecko-289454007760463", "2527 5976", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.coffe_cup_2, "The Coffee Cup", "Centro Comercial Paseo General Escalón., Paseo Gral. Escalón, San Salvador", "www.thecoffeecup.com.sv/frontend/index.php", "2219 6583", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.viva_espresso_2, "Viva Espresso", "Bulevar Del Hipodromo 644, San Salvador", "vivaespressocoffee.com/index.html", "2264 2463", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.starbucks_2, "Starbucks", "World Trade Center, Calle del mirador, San Salvador", "www.facebook.com/StarbucksElSalvador", "7069 9397", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.las_brumas_2, "Las Brumas | Grill & Cafe |", "San Salvador CP 1101", "restaurantelasbrumas.business.site/", "2508 0454", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.cafe_sunzal_2, "Cafe sunzal", "CA-2, El Sunzal", "casademarhotel.com/v2/restaurant", "2355 7137", getResources().getString(R.string.description_placeholder), "", ""));

            } else if (mLoadType == 2) {
                //TAB HOTELS
                placesList.add(new Place(R.drawable.barcelo_2, "Hotel", "Ave las Magnolias y Blvd del Hipodromo, San Salvador", "www.barcelo.com/es/barcelo-hotels/hoteles/el-salvador/san-salvador/barcelo-san-salvador/?utm_source=google&utm_medium=organic&utm_campaign=my_business&utm_content=h397", "2133 7000", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.crowne_plaza_2, "Hotel Crowne Plaza", "89 Avenida Norte y 11 Calle Poniente, San Salvador", "www.ihg.com/crowneplaza/hotels/us/en/reservation", "7859 5095", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.holiday_in_2, "Hotel Holiday Inn", "Blvd y Urbanizacion Santa Elena | Calle el pital Oriente, 400 mts al Norte de Embajada Americana, San Salvador", "www.ihg.com/holidayinn/hotels/us/en/san-salvador/sslrc/hoteldetail", "2500 6000", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.hotel_marela_2, "Hotel Marela", "Colonia Escalón,83 Avenida Sur, Calle Juan Jose Canas 5, San Salvador", "hotelmarela.com.sv/", "2507 4400", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.mirador_plaza_2, "Hotel Mirador Plaza", "Calle El Mirador 95, San Salvador", "www.fairwayhotels.com/Hotel/Hotel_Mirador_Plaza.htm", "2244 6000", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.hotel_presidente_2, "Hotel Presidente", "Avenida De La Revolucion, San Salvador", "www.marriott.com/hotels/travel/salsi-sheraton-presidente-san-salvador-hotel/?scid=bb1a189a-fec3-4d19-a255-54ba596febe2", "2283 4000", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.intercontinental_2, "Hotel Real Intercontinental", "Boulevard De Los Heroes And, San Salvador", "www.ihg.com/intercontinental/hotels/us/en/san-salvador/sslhb/hoteldetail?cm_mmc=GoogleMaps-_-IC-_-SV-_-SSLHB", "2211 3333", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.sal_luz_2, "Hotel Sal y Luz", "Col Campestre Cl A No 13-76 Ent Av Juan Ramón Molina San Salvador", "salyluzhotels.com", "2519 9595", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.terraza_2, "Hotel Terraza", "Calle Padres Aguilar, San Salvador", "www.terraza.com.sv/index.php/es/", "2565 7000", getResources().getString(R.string.description_placeholder), "", ""));

            } else {
                //TAB TOURISM
                placesList.add(new Place(R.drawable.el_tunco_2, "El Tunco", "El Tunco, La libertad, El Salvador", "-", "-", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.lago_coatepeque, "Lago de Coatepeque", "Lago de Coatepeque, El Salvador", "-", "-", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.joya_2, "Joya de Cerén", "Km 32, Carr San Juan Opico - Agua Escondida", "www.cultura.gob.sv/parque-arqueologico-joya-de-ceren/", "2401 5782", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.boqueron_2, "El Boqueron", "Calle al Parque Nacional El Boqueron, San Salvador", "-", "-", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.tazumal_2, "Tazumal", "Calle Tazumal, Chalchuapa", "-", "2444 0010", getResources().getString(R.string.description_placeholder), "", ""));
                placesList.add(new Place(R.drawable.volcan_santa_ana_2, "Volcan de Santa Ana", "Volcán de Santa Ana, El Salvador", "-", "-", getResources().getString(R.string.description_placeholder), "", ""));

            }
        }

        PlacesAdapter adapter = new PlacesAdapter(placesList, this);

        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);

        return v;
    }

    //Implementation of the interface
    @Override
    public void onItemClicked(Place place) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_PLACE, place);
        intent.putExtra(DetailsActivity.EXTRA_ACTION, DetailActions.ACTION_ADD);
        startActivity(intent);
    }

}
