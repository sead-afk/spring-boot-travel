import { useState } from 'react';
import HotelCard from '../components/HotelDetails';
import '../styles/hotels.css'

const HotelsContainer = () => {
    const [hotels, setHotels] = useState([
        {
        image: 'https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.cfmedia.vfmleonardo.com%2FimageRepo%2F6%2F0%2F100%2F3%2F616%2Fbkksi-exterior-0385-hor-clsc_O.jpg&f=1&nofb=1&ipt=11bb65e050e78037e26b35f340f879d85617f1cd764493ad107762deea59cd17&ipo=images',
        name: 'Grand Hotel',
        location: 'Paris, France',
        price: 200,
         description: 'A luxurious hotel in the heart of Paris with stunning views and excellent amenities.'
        },
        {
            // image: 'https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.cfmedia.vfmleonardo.com%2FimageRepo%2F6%2F0%2F100%2F3%2F616%2Fbkksi-exterior-0385-hor-clsc_O.jpg&f=1&nofb=1&ipt=11bb65e050e78037e26b35f340f879d85617f1cd764493ad107762deea59cd17&ipo=images',
            name: 'Grand Hotel',
            location: 'Paris, France',
            price: 200,
            description: 'A luxurious hotel in the heart of Paris with stunning views and excellent amenities.'
        },
    ])

    return (
        <div id="hotels">
            {
                hotels.map((hotel, index) => {
                    return <HotelCard hotel={hotel} key={index} />
                })
            }
        </div>
    )
}

export default HotelsContainer;