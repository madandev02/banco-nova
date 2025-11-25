export default {
  content: ["./index.html","./src/**/*.{js,jsx}"],
  theme: {
    extend: {
      colors: {
        nova: {
          primary: "#004A77",
          secondary: "#1AAFD0",
          accent: "#FFC24B",
          ink: "#0A2342",
          bg: "#F4F7FB",
          card: "#FFFFFF"
        }
      },
      boxShadow: { soft: "0 12px 30px rgba(0, 74, 119, 0.08)" },
      borderRadius: { xl2: "1rem" }
    }
  },
  plugins: []
};
