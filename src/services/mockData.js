export const mockCourses = [
  {
    id: 1,
    title: "বাংলা বর্ণমালা - Bangla Alphabet",
    description: "Learn the foundation of Bangla language with all vowels and consonants. Perfect for absolute beginners.",
    level: "Beginner",
    thumbnailUrl: "https://images.pexels.com/photos/8519791/pexels-photo-8519791.jpeg?auto=compress&cs=tinysrgb&w=800",
    price: "Free",
    enrolled: false
  },
  {
    id: 2,
    title: "প্রাথমিক কথোপকথন - Basic Conversations",
    description: "Master essential daily conversations in Bangla. Learn greetings, introductions, and common phrases.",
    level: "Beginner",
    thumbnailUrl: "https://images.pexels.com/photos/5212345/pexels-photo-5212345.jpeg?auto=compress&cs=tinysrgb&w=800",
    price: "Free",
    enrolled: false
  },
  {
    id: 3,
    title: "বাংলা ব্যাকরণ - Bangla Grammar",
    description: "Understand the structure of Bangla language with comprehensive grammar lessons.",
    level: "Intermediate",
    thumbnailUrl: "https://images.pexels.com/photos/4145153/pexels-photo-4145153.jpeg?auto=compress&cs=tinysrgb&w=800",
    price: "Paid",
    enrolled: false
  }
];

export const mockLessons = {
  1: [
    {
      id: 1,
      courseId: 1,
      title: "স্বরবর্ণ - Vowels (অ, আ, ই, ঈ)",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# স্বরবর্ণ (Vowels)

বাংলা ভাষায় ১১টি স্বরবর্ণ আছে:

**অ (O)** - শব্দের উদাহরণ: অজগর, অরণ্য
**আ (A)** - শব্দের উদাহরণ: আম, আকাশ
**ই (I)** - শব্দের উদাহরণ: ইলিশ, ইতিহাস
**ঈ (Ee)** - শব্দের উদাহরণ: ঈদ, ঈশ্বর

## Practice:
১. অ দিয়ে শুরু হওয়া শব্দ: অতিথি, অনেক
২. আ দিয়ে শুরু হওয়া শব্দ: আবার, আগুন
৩. ই দিয়ে শুরু হওয়া শব্দ: ইচ্ছা, ইমারত
৪. ঈ দিয়ে শুরু হওয়া শব্দ: ঈর্ষা, ঈগল`
    },
    {
      id: 2,
      courseId: 1,
      title: "স্বরবর্ণ - Vowels (উ, ঊ, ঋ)",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# স্বরবর্ণ (Vowels) - Part 2

**উ (U)** - শব্দের উদাহরণ: উট, উনিশ
**ঊ (Oo)** - শব্দের উদাহরণ: ঊর্ধ্ব, ঊষা
**ঋ (Ri)** - শব্দের উদাহরণ: ঋতু, ঋষি

## Practice:
১. উ দিয়ে শুরু হওয়া শব্দ: উপহার, উত্তর
২. ঊ দিয়ে শুরু হওয়া শব্দ: ঊনসত্তর
৩. ঋ দিয়ে শুরু হওয়া শব্দ: ঋণ`
    },
    {
      id: 3,
      courseId: 1,
      title: "ব্যঞ্জনবর্ণ - Consonants (ক-বর্গ)",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# ব্যঞ্জনবর্ণ - Consonants

বাংলা ভাষায় ৩৯টি ব্যঞ্জনবর্ণ আছে।

## ক-বর্গ (Ka Series):
**ক (Ka)** - শব্দ: কলা, কাগজ
**খ (Kha)** - শব্দ: খবর, খাবার
**গ (Ga)** - শব্দ: গান, গাড়ি
**ঘ (Gha)** - শব্দ: ঘর, ঘাস
**ঙ (Nga)** - শব্দ: বাঙালি, অঙ্ক

## Practice:
এই বর্ণগুলো দিয়ে বানানো সহজ শব্দ অনুশীলন করুন।`
    },
    {
      id: 4,
      courseId: 1,
      title: "ব্যঞ্জনবর্ণ - Consonants (চ-বর্গ, ট-বর্গ)",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# ব্যঞ্জনবর্ণ - Part 2

## চ-বর্গ (Cha Series):
**চ (Cha)** - শব্দ: চা, চাঁদ
**ছ (Chha)** - শব্দ: ছবি, ছাত্র
**জ (Ja)** - শব্দ: জল, জানালা
**ঝ (Jha)** - শব্দ: ঝড়, ঝরনা
**ঞ (Nja)** - শব্দ: বাঞ্ছা, যাঞ্জ

## ট-বর্গ (Ta Series):
**ট (Ta)** - শব্দ: টাকা, টমেটো
**ঠ (Tha)** - শব্দ: ঠিকানা, ঠাকুর
**ড (Da)** - শব্দ: ডাক, ডিম
**ঢ (Dha)** - শব্দ: ঢাকা, ঢেউ
**ণ (Na)** - শব্দ: গণিত, বাণী`
    },
    {
      id: 5,
      courseId: 1,
      title: "যুক্তাক্ষর - Compound Letters",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# যুক্তাক্ষর (Compound Letters)

দুই বা ততোধিক ব্যঞ্জনবর্ণ মিলে যুক্তাক্ষর তৈরি হয়।

## সাধারণ যুক্তাক্ষর:
**ক্ক** = ক + ক (উদাহরণ: পাক্কা)
**ক্ত** = ক + ত (উদাহরণ: রাক্ত)
**ক্ষ** = ক + ষ (উদাহরণ: শিক্ষা)
**ঙ্গ** = ঙ + গ (উদাহরণ: অঙ্গ)
**চ্ছ** = চ + ছ (উদাহরণ: ইচ্ছা)
**জ্ঞ** = জ + ঞ (উদাহরণ: জ্ঞান)
**ত্র** = ত + র (উদাহরণ: পত্র)
**দ্ধ** = দ + ধ (উদাহরণ: বুদ্ধ)
**ন্ত** = ন + ত (উদাহরণ: ছন্ত)
**ব্দ** = ব + দ (উদাহরণ: শব্দ)

## Practice:
এই যুক্তাক্ষরগুলো লিখুন এবং উচ্চারণ করুন।`
    }
  ],
  2: [
    {
      id: 6,
      courseId: 2,
      title: "অভিবাদন - Greetings",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# অভিবাদন (Greetings)

## সাধারণ অভিবাদন:

**নমস্কার (Nomoshkar)** - Hello (formal)
**আসসালামু আলাইকুম (Assalamu Alaikum)** - Peace be upon you (Muslim greeting)
**সুপ্রভাত (Suprobhat)** - Good morning
**শুভ সন্ধ্যা (Shubho Shondha)** - Good evening
**শুভ রাত্রি (Shubho Ratri)** - Good night

## কথোপকথন উদাহরণ:
**ব্যক্তি ১:** নমস্কার! আপনি কেমন আছেন?
**Person 1:** Hello! How are you?

**ব্যক্তি ২:** আমি ভালো আছি, ধন্যবাদ। আপনি?
**Person 2:** I am fine, thank you. And you?

**ব্যক্তি ১:** আমিও ভালো।
**Person 1:** I am also fine.`
    },
    {
      id: 7,
      courseId: 2,
      title: "পরিচয় - Introduction",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# পরিচয় (Introduction)

## নিজের পরিচয় দেওয়া:

**আমার নাম...** (Amar nam...) - My name is...
**আমি...থেকে এসেছি** (Ami...theke eshechi) - I am from...
**আমি একজন...** (Ami ekjon...) - I am a...

## কথোপকথন:
**প্রশ্ন:** আপনার নাম কী?
**Question:** What is your name?
**উত্তর:** আমার নাম জন।
**Answer:** My name is John.

**প্রশ্ন:** আপনি কোথা থেকে এসেছেন?
**Question:** Where are you from?
**উত্তর:** আমি আমেরিকা থেকে এসেছি।
**Answer:** I am from America.

**প্রশ্ন:** আপনি কী করেন?
**Question:** What do you do?
**উত্তর:** আমি একজন শিক্ষার্থী।
**Answer:** I am a student.`
    },
    {
      id: 8,
      courseId: 2,
      title: "সংখ্যা - Numbers",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# সংখ্যা (Numbers)

## ১-১০:
১ (এক) - Ek - One
২ (দুই) - Dui - Two
৩ (তিন) - Teen - Three
৪ (চার) - Char - Four
৫ (পাঁচ) - Pach - Five
৬ (ছয়) - Choy - Six
৭ (সাত) - Shat - Seven
৮ (আট) - Aat - Eight
৯ (নয়) - Noy - Nine
১০ (দশ) - Dosh - Ten

## ১১-২০:
১১ (এগারো) - Egaro - Eleven
১২ (বারো) - Baro - Twelve
১৫ (পনেরো) - Ponero - Fifteen
২০ (বিশ) - Bish - Twenty

## বড় সংখ্যা:
১০০ (একশ) - Eksho - Hundred
১০০০ (এক হাজার) - Ek Hajar - Thousand`
    },
    {
      id: 9,
      courseId: 2,
      title: "দৈনন্দিন বাক্য - Daily Phrases",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# দৈনন্দিন বাক্য (Daily Phrases)

## সাধারণ বাক্য:

**ধন্যবাদ** (Dhonnobad) - Thank you
**দয়া করে** (Doya kore) - Please
**দুঃখিত** (Dukhito) - Sorry
**হ্যাঁ** (Ha) - Yes
**না** (Na) - No
**বুঝতে পারছি না** (Bujhte parchi na) - I don't understand
**আবার বলুন** (Abar bolun) - Please say again
**সাহায্য করুন** (Shahajjo korun) - Please help

## কথোপকথন:
**প্রশ্ন:** এটা কী?
**Question:** What is this?
**উত্তর:** এটা একটা বই।
**Answer:** This is a book.

**প্রশ্ন:** এটার দাম কত?
**Question:** What is the price?
**উত্তর:** পাঁচশ টাকা।
**Answer:** Five hundred taka.`
    },
    {
      id: 10,
      courseId: 2,
      title: "খাবারের অর্ডার - Ordering Food",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# খাবারের অর্ডার (Ordering Food)

## রেস্তোরাঁয় কথোপকথন:

**আমি...খেতে চাই** (Ami...khete chai) - I want to eat...
**মেনু দেখাবেন?** (Menu dekhaben?) - Can I see the menu?
**বিল আনুন** (Bill anun) - Bring the bill

## খাবার:
**ভাত** (Bhat) - Rice
**রুটি** (Ruti) - Bread
**মাছ** (Mach) - Fish
**মাংস** (Mangsho) - Meat
**সবজি** (Shobji) - Vegetables
**পানি** (Pani) - Water
**চা** (Cha) - Tea

## কথোপকথন উদাহরণ:
**গ্রাহক:** আমি ভাত আর মাছ খেতে চাই।
**Customer:** I want to eat rice and fish.

**ওয়েটার:** আর কিছু লাগবে?
**Waiter:** Anything else?

**গ্রাহক:** এক গ্লাস পানি দিন।
**Customer:** Give me one glass of water.`
    }
  ],
  3: [
    {
      id: 11,
      courseId: 3,
      title: "বিশেষ্য - Nouns",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# বিশেষ্য (Nouns)

বিশেষ্য হলো যা কিছু নাম বোঝায় - ব্যক্তি, স্থান, বস্তু বা ভাব।

## বিশেষ্যের প্রকারভেদ:

### ১. জাতিবাচক বিশেষ্য (Common Nouns):
**ছাত্র** (Student), **শিক্ষক** (Teacher), **বই** (Book), **শহর** (City)

### ২. ব্যক্তিবাচক বিশেষ্য (Proper Nouns):
**রহিম** (Rahim), **ঢাকা** (Dhaka), **বাংলাদেশ** (Bangladesh)

### ৩. বস্তুবাচক বিশেষ্য (Material Nouns):
**সোনা** (Gold), **লোহা** (Iron), **পানি** (Water)

### ৪. সমষ্টিবাচক বিশেষ্য (Collective Nouns):
**দল** (Team), **পরিবার** (Family), **সভা** (Meeting)

### ৫. ভাববাচক বিশেষ্য (Abstract Nouns):
**সুখ** (Happiness), **দুঃখ** (Sadness), **প্রেম** (Love)

## উদাহরণ বাক্য:
**ছাত্ররা বই পড়ছে।** (Students are reading books.)
**ঢাকা বাংলাদেশের রাজধানী।** (Dhaka is the capital of Bangladesh.)`
    },
    {
      id: 12,
      courseId: 3,
      title: "সর্বনাম - Pronouns",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# সর্বনাম (Pronouns)

বিশেষ্যের পরিবর্তে ব্যবহৃত শব্দকে সর্বনাম বলে।

## ব্যক্তিবাচক সর্বনাম:

### প্রথম পুরুষ (First Person):
**আমি** (Ami) - I
**আমরা** (Amra) - We

### দ্বিতীয় পুরুষ (Second Person):
**তুমি** (Tumi) - You (informal)
**আপনি** (Apni) - You (formal)
**তোমরা** (Tomra) - You all (informal)
**আপনারা** (Apnara) - You all (formal)

### তৃতীয় পুরুষ (Third Person):
**সে** (She) - He/She (informal)
**তিনি** (Tini) - He/She (formal)
**তারা** (Tara) - They

## সম্বন্ধবাচক সর্বনাম:
**আমার** (Amar) - My
**তোমার** (Tomar) - Your (informal)
**আপনার** (Apnar) - Your (formal)
**তার** (Tar) - His/Her

## উদাহরণ:
**আমি একজন শিক্ষার্থী।** (I am a student.)
**তিনি আমার শিক্ষক।** (He/She is my teacher.)`
    },
    {
      id: 13,
      courseId: 3,
      title: "ক্রিয়া - Verbs",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# ক্রিয়া (Verbs)

যে শব্দ দ্বারা কোনো কাজ করা বোঝায় তাকে ক্রিয়া বলে।

## সাধারণ ক্রিয়া:

**যাওয়া** (Jaoa) - To go
**আসা** (Asha) - To come
**খাওয়া** (Khaoa) - To eat
**পড়া** (Pora) - To read
**লেখা** (Lekha) - To write
**বলা** (Bola) - To speak
**শোনা** (Shona) - To hear
**দেখা** (Dekha) - To see

## কালের রূপ (Tenses):

### বর্তমান কাল (Present):
**আমি যাচ্ছি** (I am going)
**তুমি খাচ্ছ** (You are eating)

### অতীত কাল (Past):
**আমি গিয়েছিলাম** (I went/had gone)
**তুমি খেয়েছিলে** (You ate/had eaten)

### ভবিষ্যৎ কাল (Future):
**আমি যাব** (I will go)
**তুমি খাবে** (You will eat)

## উদাহরণ বাক্য:
**সে স্কুলে যায়।** (He/She goes to school.)
**আমি ভাত খাচ্ছি।** (I am eating rice.)`
    },
    {
      id: 14,
      courseId: 3,
      title: "বিশেষণ - Adjectives",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# বিশেষণ (Adjectives)

যে শব্দ বিশেষ্য বা সর্বনামের দোষ, গুণ, অবস্থা, সংখ্যা ইত্যাদি প্রকাশ করে তাকে বিশেষণ বলে।

## গুণবাচক বিশেষণ:
**ভালো** (Bhalo) - Good
**খারাপ** (Kharap) - Bad
**সুন্দর** (Shundor) - Beautiful
**বড়** (Boro) - Big
**ছোট** (Chhoto) - Small
**লম্বা** (Lomba) - Tall/Long
**খাটো** (Khato) - Short

## রঙের বিশেষণ:
**লাল** (Lal) - Red
**সাদা** (Shada) - White
**কালো** (Kalo) - Black
**নীল** (Neel) - Blue
**সবুজ** (Shobuj) - Green

## সংখ্যাবাচক বিশেষণ:
**একটি** (Ekti) - One
**কয়েকটি** (Koyekti) - Few
**অনেক** (Onek) - Many

## উদাহরণ বাক্য:
**এটি একটি সুন্দর ফুল।** (This is a beautiful flower.)
**সে লম্বা ছেলে।** (He is a tall boy.)
**আমার তিনটি বই আছে।** (I have three books.)`
    },
    {
      id: 15,
      courseId: 3,
      title: "বাক্য গঠন - Sentence Structure",
      videoUrl: "https://www.youtube.com/embed/bUtVkW7pAcs",
      content: `# বাক্য গঠন (Sentence Structure)

বাংলা বাক্যের সাধারণ গঠন: **কর্তা + কর্ম + ক্রিয়া**

## সরল বাক্য (Simple Sentences):

**আমি ভাত খাই।**
(Ami bhat khai.)
I eat rice.
কর্তা: আমি | কর্ম: ভাত | ক্রিয়া: খাই

**সে বই পড়ে।**
(She boi pore.)
He/She reads books.
কর্তা: সে | কর্ম: বই | ক্রিয়া: পড়ে

## জটিল বাক্য (Complex Sentences):

**যখন বৃষ্টি হয়, আমি ঘরে থাকি।**
(Jokhon brishti hoy, ami ghore thaki.)
When it rains, I stay home.

**যদি তুমি আসো, আমি খুশি হব।**
(Jodi tumi asho, ami khushi hobo.)
If you come, I will be happy.

## প্রশ্নবোধক বাক্য (Questions):

**তুমি কোথায় যাচ্ছ?** (Where are you going?)
**তোমার নাম কী?** (What is your name?)
**সে কখন আসবে?** (When will he/she come?)

## নেতিবাচক বাক্য (Negative):

**আমি যাব না।** (I will not go.)
**সে খায়নি।** (He/She did not eat.)

## Practice:
এই গঠন অনুসরণ করে নিজের বাক্য তৈরি করুন।`
    }
  ]
};

export const getUserEnrollments = () => {
  const enrollments = localStorage.getItem('enrollments');
  return enrollments ? JSON.parse(enrollments) : [];
};

export const enrollInCourse = (courseId) => {
  const enrollments = getUserEnrollments();
  if (!enrollments.includes(courseId)) {
    enrollments.push(courseId);
    localStorage.setItem('enrollments', JSON.stringify(enrollments));
  }
  return enrollments;
};

export const isEnrolled = (courseId) => {
  const enrollments = getUserEnrollments();
  return enrollments.includes(courseId);
};
