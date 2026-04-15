package org.lxc.qysendserver.util;

import java.time.LocalTime;
import java.util.Random;

public class WeatherGreetingUtil {
    private static final String[] MORNING_GREETINGS = {
        "早上好！新的一天开始了，祝你今天心情愉快！",
        "清晨的第一缕阳光，带来了新的希望，愿你今天一切顺利！",
        "早安！今天又是美好的一天，加油！",
        "早上好，愿你今天充满活力，收获满满！",
        "清晨好！愿你的一天像阳光一样灿烂！"
    };
    
    private static final String[] AFTERNOON_GREETINGS = {
        "下午好！工作辛苦了，记得适当休息哦！",
        "午后时光，愿你精神饱满，继续加油！",
        "下午好，享受这美好的午后时光！",
        "午后好！愿你的下午充满效率和快乐！",
        "下午好，记得补充水分，保持精力充沛！"
    };
    
    private static final String[] EVENING_GREETINGS = {
        "晚上好！一天的工作结束了，好好放松一下吧！",
        "傍晚好！愿你有一个愉快的夜晚！",
        "晚上好，享受属于自己的休闲时光！",
        "夜幕降临，愿你今晚睡得香甜！",
        "晚上好！今天辛苦了，好好休息！"
    };
    
    private static final String[][] WEATHER_TIPS = {
        // 晴天
        {
            "天气晴朗，适合户外活动，记得涂防晒霜！",
            "阳光明媚，是晒被子的好天气！",
            "晴空万里，不妨出去走走，感受大自然的美好！",
            "天气真好，适合拍照留念哦！",
            "阳光灿烂，记得多喝水，补充水分！"
        },
        // 多云
        {
            "多云天气，温度适宜，是出门的好时机！",
            "云层较多，紫外线不强，适合户外活动！",
            "多云转晴，天气多变，记得带件外套！",
            "云层遮挡了部分阳光，体感舒适！",
            "多云天气，适合进行户外运动！"
        },
        // 阴天
        {
            "阴天光线较暗，注意室内照明！",
            "阴天温度适宜，但可能会有小阵雨，记得带伞！",
            "阴天气压较低，注意保持心情舒畅！",
            "阴天适合室内活动，如读书、看电影等！",
            "阴天使然，适合在家做顿美食！"
        },
        // 雨天
        {
            "雨天路滑，出门注意安全！",
            "雨天适合在家听音乐、看书，享受宁静时光！",
            "雨天记得带伞，避免淋雨感冒！",
            "雨后空气清新，不妨开窗通风！",
            "雨天路面积水，驾车注意减速！"
        },
        // 雪天
        {
            "雪天路滑，出行注意安全！",
            "雪景虽美，但也要注意保暖！",
            "雪天适合堆雪人、打雪仗，享受冬日乐趣！",
            "雪后道路结冰，驾车要特别小心！",
            "雪天记得穿防滑鞋，避免滑倒！"
        },
        // 高温
        {
            "高温天气，注意防暑降温！",
            "炎热天气，多喝水，避免中暑！",
            "高温时段尽量避免户外活动！",
            "注意防晒，涂防晒霜，戴遮阳帽！",
            "高温天气，饮食宜清淡，多吃蔬菜水果！"
        },
        // 低温
        {
            "低温天气，注意保暖！",
            "天气寒冷，记得添衣保暖！",
            "低温天气，适合喝热饮，暖身暖心！",
            "寒冷天气，注意室内通风，预防感冒！",
            "低温天气，运动前要充分热身！"
        }
    };
    
    private static final Random RANDOM = new Random();
    
    /**
     * 根据时间获取问候语
     */
    public static String getGreeting() {
        LocalTime now = LocalTime.now();
        if (now.isBefore(LocalTime.NOON)) {
            return MORNING_GREETINGS[RANDOM.nextInt(MORNING_GREETINGS.length)];
        } else if (now.isBefore(LocalTime.of(18, 0))) {
            return AFTERNOON_GREETINGS[RANDOM.nextInt(AFTERNOON_GREETINGS.length)];
        } else {
            return EVENING_GREETINGS[RANDOM.nextInt(EVENING_GREETINGS.length)];
        }
    }
    
    /**
     * 根据天气和温度获取生活建议
     */
    public static String getWeatherTip(String weather, int temperature) {
        int tipIndex;
        
        if (weather.contains("雨")) {
            tipIndex = 3; // 雨天
        } else if (weather.contains("雪")) {
            tipIndex = 4; // 雪天
        } else if (weather.equals("晴")) {
            tipIndex = 0; // 晴天
        } else if (weather.equals("多云")) {
            tipIndex = 1; // 多云
        } else if (weather.equals("阴")) {
            tipIndex = 2; // 阴天
        } else if (temperature >= 35) {
            tipIndex = 5; // 高温
        } else if (temperature <= 0) {
            tipIndex = 6; // 低温
        } else {
            tipIndex = 1; // 默认多云
        }
        
        String[] tips = WEATHER_TIPS[tipIndex];
        return tips[RANDOM.nextInt(tips.length)];
    }
    
    /**
     * 根据温度获取穿衣建议
     */
    public static String getClothingSuggestion(int temperature) {
        if (temperature >= 30) {
            return "建议穿短袖、短裤、轻薄透气的衣物。";
        } else if (temperature >= 20) {
            return "建议穿薄外套、长袖衬衫或T恤。";
        } else if (temperature >= 10) {
            return "建议穿毛衣、夹克等保暖衣物。";
        } else if (temperature >= 0) {
            return "建议穿棉衣、羽绒服等厚实保暖的衣物。";
        } else {
            return "建议穿厚羽绒服、毛衣、保暖裤等多层保暖衣物。";
        }
    }
}