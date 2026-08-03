<div align="center">

# 🧭 Career Compass

**AI-powered career guidance Android app — Gemini-driven career matching, MVVM architecture, Firebase backend.**

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)](https://developer.android.com)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-23-blue)](app/build.gradle)
[![Gemini](https://img.shields.io/badge/AI-Gemini-4285F4?logo=google&logoColor=white)](https://ai.google.dev)
[![Firebase](https://img.shields.io/badge/Backend-Firestore-FFCA28?logo=firebase&logoColor=black)](https://firebase.google.com)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

</div>

---

## What it does

Career Compass interviews the user about their skills, interests, strengths, and preferred work environment, then uses **Google Gemini** to generate ranked career-path recommendations — each with a fit percentage and a matching-factor breakdown — plus deep-dive analysis and small-business guidance for any chosen path.

## ✨ Features

- 🤖 **Gemini-powered career matching** — structured JSON responses parsed into ranked career cards with fit scores
- 💬 **AI career chat** — follow-up guidance conversation per career path
- 👤 **Full auth flow** — register, login, forgot password, profile completion (Firebase Auth)
- ☁️ **Cloud profiles** — education, experience, and skills stored in Firestore
- 🔔 **Notifications center**
- 🎨 Material Design, Lottie animations, splash screen API

## 🏗 Architecture

MVVM throughout — each screen is a Fragment/Activity with its own ViewModel and LiveData:

```
ui/
├── auth/        login, register, forgot-password, complete-profile (+ ViewModels)
├── main/        home, career chat, notifications (+ ViewModels)
├── result/      Gemini call + ranked career results
└── base/        shared activity plumbing
beans/           user, career-path, chat models (Gson)
```

- **AI:** Google Generative AI SDK (`gemini-pro`)
- **Backend:** Firebase Auth + Firestore
- **Navigation:** Jetpack Navigation Component
- **Logging:** Timber

## 🚀 Getting Started

```bash
git clone https://github.com/ProHussain/CareerCompass-AI.git
```

1. Create a Firebase project (Auth + Firestore) and drop **your own** `google-services.json` into `app/`
   — see `google-services.example.json` for the expected shape
2. Get a Gemini API key from [Google AI Studio](https://aistudio.google.com/) and add to `local.properties`:
   ```properties
   GEMINI_API_KEY=your_key_here
   ```
3. Sync Gradle, run on API 23+

> The app intentionally ships no credentials — both Firebase config and the Gemini key are supplied locally.

## 🗺 Roadmap

- [ ] Screenshots & demo GIF
- [ ] Migrate Gemini calls to the current Google AI SDK + streaming responses
- [ ] Kotlin migration
- [ ] Repository layer + unit tests for prompt/response parsing
- [ ] CI build via GitHub Actions

## 👤 Author

**Ghulam Hussain** — Product Engineer · apps with 50M+ combined downloads
[LinkedIn](https://www.linkedin.com/in/prohussain/) · [GitHub](https://github.com/ProHussain) · [Medium](https://medium.com/@pro_hussain)

## 📄 License

[MIT](LICENSE)
