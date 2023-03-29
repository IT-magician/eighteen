/// <reference types="react-scripts" />

declare namespace NodeJS {
  interface ProcessEnv {
    NODE_ENV: "development" | "production" | "test";
    REACT_APP_OPEN_WEATHER_API_KEY: string;
    REACT_APP_SERVER_URL: string;
    REACT_APP_TEST_URL: string;
  }
}
