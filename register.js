// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyCHAM9htMsE3hcqLrldfu7MEkBlSONz5hs",
  authDomain: "ecommercestore-1dd9f.firebaseapp.com",
  projectId: "ecommercestore-1dd9f",
  storageBucket: "ecommercestore-1dd9f.firebasestorage.app",
  messagingSenderId: "557253256597",
  appId: "1:557253256597:web:63d07e3b4d2d70f346c29c",
  measurementId: "G-23HR84Z6TF"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);